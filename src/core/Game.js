/**
 * Game.js — the orchestrator: finite state machine, phase logic, and the
 * per-frame update that ties player, camera, world, and UI together.
 *
 * FSM phases (1 Samuel 30):
 *   STATE_0_ZIKLAG           — ashes, debris trail, Desert Edge waypoint
 *   STATE_1_DESERT_MARCH     — dunes, stamina, the Brook Besor
 *   STATE_2_EGYPTIAN_SERVANT — dialogue tree, then the directional arrow
 *   STATE_3_AMALEKITE_RESCUE — vision cones, the cage, victory
 */
import * as THREE from 'three';
import { StateMachine } from './StateMachine.js';
import { World } from '../world/World.js';
import { Player } from '../player/Player.js';
import { ThirdPersonCamera } from '../camera/ThirdPersonCamera.js';
import { DirectionalArrow } from '../entities/DirectionalArrow.js';
import { HUD } from '../ui/HUD.js';
import { DialogueModal } from '../ui/DialogueModal.js';
import { VictoryScreen } from '../ui/VictoryScreen.js';
import {
  STATES,
  NARRATIVE,
  OBJECTIVES,
  CONTROLS,
  VISION,
} from '../constants.js';
import { distanceXZ } from '../utils/math.js';
import { ZIKLAG } from '../world/zones/ZiklagZone.js';
import { DESERT } from '../world/zones/DesertZone.js';
import { EGYPT } from '../world/zones/EgyptianZone.js';
import { CAMP } from '../world/zones/CampZone.js';

export class Game {
  constructor({ scene, camera, input }) {
    this.scene = scene;
    this.camera = camera;
    this.input = input;

    this.world = new World(scene);
    this.player = new Player(input);
    this.cameraRig = new ThirdPersonCamera(camera, input);
    this.arrow = new DirectionalArrow();
    scene.add(this.player.group, this.arrow.group);

    this.hud = new HUD();
    this.modal = new DialogueModal(input);
    this.victory = new VictoryScreen(() => this.resetAll());

    // -------- run flags --------
    this.started = false;
    this.finished = false;
    this.brookTriggered = false;
    this.ziklagTriggered = false;
    this.servantServed = false;
    this._detectionCooldown = 0;
    this._nudgeCooldown = 0;
    this._prompt = null;

    this.fsm = new StateMachine('PURSUE');
    this._buildStates();
  }

  // ================================================================ FSM
  _buildStates() {
    this.fsm.add(STATES.ZIKLAG, {
      enter: () => {
        this._enterZone(STATES.ZIKLAG);
        this.hud.setObjective(OBJECTIVES[STATES.ZIKLAG]);
      },
      update: () => {
        const wp = ZIKLAG.waypoint;
        const dist = distanceXZ(this.player.position, wp);

        if (dist < 7 && !this.ziklagTriggered) {
          this._setPrompt(NARRATIVE.promptWaypoint);
        } else {
          this._setPrompt(null);
        }

        if (!this.ziklagTriggered && (dist < 4.2 || (dist < 7 && this.player.interactRequested))) {
          this.ziklagTriggered = true;
          this._setPrompt(null);
          this.modal.showNarrative(NARRATIVE.ziklagModal, () => {
            this.fsm.set(STATES.DESERT);
          });
        }
      },
    });

    this.fsm.add(STATES.DESERT, {
      enter: () => {
        this._enterZone(STATES.DESERT);
        this.hud.setObjective(OBJECTIVES[STATES.DESERT]);
      },
      update: () => {
        const p = this.player.position;

        // --- the Brook Besor ---
        if (!this.brookTriggered && Math.abs(p.z - DESERT.brookZ) < DESERT.brookTrigger) {
          this.brookTriggered = true;
          this.modal.showNarrative(NARRATIVE.brookModal);
        }

        // --- far edge of the march → the arid field ---
        if (distanceXZ(p, DESERT.exit) < 6.5) {
          this.fsm.set(STATES.EGYPTIAN);
        }
      },
    });

    this.fsm.add(STATES.EGYPTIAN, {
      enter: () => {
        this._enterZone(STATES.EGYPTIAN);
        this.hud.setObjective(OBJECTIVES[STATES.EGYPTIAN]);
      },
      update: () => {
        const p = this.player.position;
        const servantPos = EGYPT.servant;
        const dist = distanceXZ(p, servantPos);

        if (!this.servantServed) {
          if (dist < 3) {
            this._setPrompt(NARRATIVE.promptServant);
            if (this.player.interactRequested) {
              this._setPrompt(null);
              this._startServantDialogue();
            }
          } else {
            this._setPrompt(null);
          }
          // keep the player oriented on the servant, not the exit
          if (distanceXZ(p, EGYPT.exit) < 7 && !this.modal.isOpen) {
            this._nudgeCooldown = Math.max(0, this._nudgeCooldown - 1 / 60);
            if (this._nudgeCooldown <= 0) {
              this._nudgeCooldown = 2.2;
              this.hud.showAlert('You must find the Egyptian servant first.', 1.8);
            }
            // gentle push back toward the field (south = +z)
            p.z = Math.max(p.z, EGYPT.exit.z + 13);
          }
        } else if (distanceXZ(p, EGYPT.exit) < 6.5) {
          this.fsm.set(STATES.CAMP);
        }
      },
    });

    this.fsm.add(STATES.CAMP, {
      enter: () => {
        this._enterZone(STATES.CAMP);
        this.hud.setObjective(OBJECTIVES[STATES.CAMP]);
        this.arrow.setTarget(CAMP.captives.x, CAMP.captives.z);
      },
      update: (dt) => {
        const p = this.player.position;
        this._detectionCooldown = Math.max(0, this._detectionCooldown - dt);

        // --- vision cones (distance + facing) ---
        const zone = this.world.activeZone;
        const { detected } = zone.checkDetection(p);
        if (detected && this._detectionCooldown <= 0 && !this.modal.isOpen) {
          this._detectionCooldown = VISION.alertDuration;
          this.hud.showAlert(NARRATIVE.detectionAlert, VISION.alertDuration);
          // driven back to the valley mouth
          p.x = CAMP.spawn.x;
          p.z = CAMP.spawn.z;
          this.player.velocity.set(0, 0, 0);
          this.cameraRig.reset(p, CAMP.spawn.yaw + Math.PI);
        }

        // --- rescue ---
        if (distanceXZ(p, CAMP.captives) < CAMP.rescueRadius) {
          this.win();
        }
      },
    });
  }

  // ============================================================ helpers
  _enterZone(stateName) {
    const zone = this.world.activate(stateName);
    this.player.spawn(
      new THREE.Vector3(
        zone.spawn.x,
        zone.groundHeight(zone.spawn.x, zone.spawn.z),
        zone.spawn.z,
      ),
      zone.spawn.yaw,
    );
    this.player.groundHeight = zone.groundHeight(zone.spawn.x, zone.spawn.z);
    this.cameraRig.reset(this.player.position, zone.spawn.yaw + Math.PI);
    this._setPrompt(null);
    this._detectionCooldown = 0;
  }

  _setPrompt(text) {
    if (text === this._prompt) return;
    this._prompt = text;
    if (text) this.hud.showPrompt(text);
    else this.hud.hidePrompt();
  }

  _startServantDialogue() {
    this.modal.showSequence(NARRATIVE.servantDialogue, () => {
      this.servantServed = true;
      this.hud.setObjective(OBJECTIVES.SERVANT_LEAD);
      this.hud.showAlert('The servant will lead you down to the raiders.', 2.6);
      this.arrow.setTarget(EGYPT.exit.x, EGYPT.exit.z);
      this.arrow.show();
    });
  }

  // ============================================================ control
  start() {
    this.started = true;
    this.finished = false;
    this.fsm.set(STATES.ZIKLAG);
    this.input.requestLock();
  }

  win() {
    if (this.finished) return;
    this.finished = true;
    this._setPrompt(null);
    this.hud.setVisible(false);
    this.input.enabled = false;
    this.input.exitLock();
    this.arrow.hide();
    this.victory.show();
  }

  /** Full reset back to STATE_0 (Play Again). */
  resetAll() {
    this.finished = false;
    this.brookTriggered = false;
    this.ziklagTriggered = false;
    this.servantServed = false;
    this._detectionCooldown = 0;

    this.victory.hide();
    this.hud.setVisible(true);
    this.input.enabled = true;

    const servantZone = this.world.zones.get(STATES.EGYPTIAN);
    servantZone.servant.update(0, false);

    this.arrow.hide();
    this.fsm.set(STATES.ZIKLAG);
    this.input.requestLock();
  }

  // ============================================================= update
  update(dt, elapsed) {
    if (!this.started) return;

    // Modal takes priority: advance it on E / Space, freeze gameplay.
    if (this.modal.isOpen) {
      if (this.input.wasPressed(CONTROLS.interact)) this.modal.tryAdvanceFromKeyboard();
      this.hud.update(dt);
      this.world.update(dt, elapsed, this.player.position);
      this.cameraRig.update(dt, this.player.position);
      this.input.endFrame();
      return;
    }

    if (!this.finished) {
      // --- player ---
      const zone = this.world.activeZone;
      this.player.groundHeight = zone.groundHeight(this.player.position.x, this.player.position.z);
      this.player.update(
        dt,
        this.cameraRig.yaw,
        elapsed,
        zone.bounds,
        zone.colliders,
      );

      // --- world & actors ---
      this.world.update(dt, elapsed, this.player.position);
      if (zone.servant) zone.servant.update(elapsed, this.servantServed);
      this.arrow.update(dt, this.player.position);

      // --- camera ---
      this.cameraRig.update(dt, this.player.position);

      // --- FSM phase logic ---
      this.fsm.update(dt);
      this.player.interactRequested = false;

      // --- HUD ---
      this.hud.setStamina(this.player.stamina.ratio, this.player.stamina.exhausted);
      this.hud.setCompass(this.cameraRig.headingDegrees());
    } else {
      // victory: keep the scene breathing behind the overlay
      this.world.update(dt, elapsed, this.player.position);
      this.cameraRig.update(dt, this.player.position);
    }

    this.hud.update(dt);
    this.input.endFrame();
  }
}
