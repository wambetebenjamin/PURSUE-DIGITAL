/**
 * EgyptianServant.js — the seated NPC from 1 Samuel 30:11-13.
 * Idle breathing + a slow head scan; nothing else until the player interacts.
 */
import * as THREE from 'three';
import { Mat } from '../utils/materials.js';
import { createHumanoid } from '../world/props/PropFactory.js';

export function createServant() {
  const group = new THREE.Group();

  const human = createHumanoid(
    {
      skin: Mat.skinDark(),
      cloth: Mat.tunicServant(),
      cloth2: Mat.tunicCaptive(),
      hair: Mat.hair(),
    },
    'sitting',
  );
  group.add(human);

  // a small water jar beside him (bread & water beat)
  const jar = new THREE.Mesh(new THREE.SphereGeometry(0.28, 10, 8), Mat.tunicCaptive());
  jar.position.set(0.95, 0.28, 0.55);
  jar.scale.y = 1.25;
  jar.castShadow = true;
  const jarNeck = new THREE.Mesh(new THREE.CylinderGeometry(0.12, 0.15, 0.2, 8), Mat.tunicCaptive());
  jarNeck.position.set(0.95, 0.62, 0.55);
  group.add(jar, jarNeck);

  const parts = human.userData.parts;

  return {
    group,
    human,
    /**
     * @param {number} t elapsed seconds
     * @param {boolean} [served] after the player gives bread & water
     */
    update(t, served = false) {
      // breathing
      const breathe = Math.sin(t * 1.35) * 0.02;
      parts.torso.scale.y = 0.8 * (1 + breathe);
      parts.head.position.y = (served ? 1.62 : 1.62) + breathe * 0.5;

      // slow weary head turn, or an attentive look once served
      parts.head.rotation.y = Math.sin(t * (served ? 0.5 : 0.28)) * (served ? 0.45 : 0.3);

      // arm settle
      parts.armL.rotation.x = -0.75 + Math.sin(t * 0.9) * 0.05;
      parts.armR.rotation.x = -0.75 + Math.cos(t * 1.1) * 0.05;
    },
  };
}
