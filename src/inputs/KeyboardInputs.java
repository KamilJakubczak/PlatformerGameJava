package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
        private GamePanel gamePanel;
        public KeyboardInputs(GamePanel gamePanel) {
                this.gamePanel = gamePanel;
        }
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                    case KeyEvent.VK_W -> this.gamePanel.changeYDelta(-5);
                    case KeyEvent.VK_S -> this.gamePanel.changeYDelta(+5);
                    case KeyEvent.VK_A ->  this.gamePanel.changeXDelta(-5);
                    case KeyEvent.VK_D ->  this.gamePanel.changeXDelta(+5);
                    }}}