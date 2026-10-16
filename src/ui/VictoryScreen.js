/**
 * VictoryScreen.js — stylized end-of-game overlay with restart.
 */
export class VictoryScreen {
  constructor(onRestart) {
    this.root = document.getElementById('victory-screen');
    this.button = document.getElementById('restart-button');
    this.button.addEventListener('click', () => onRestart());
    this.visible = false;
  }

  show() {
    this.root.classList.remove('hidden');
    this.root.classList.remove('fade-in');
    void this.root.offsetWidth;
    this.root.classList.add('fade-in');
    this.visible = true;
  }

  hide() {
    this.root.classList.add('hidden');
    this.visible = false;
  }
}
