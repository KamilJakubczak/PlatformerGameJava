package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.CanMoveHere;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 1.0f;
    private int[][] lvlData;
    private float xDrawOffset = 21.0f * Game.SCALE;
    private float yDrawOffset = 4.0f* Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x,y,20*Game.SCALE,28*Game.SCALE);
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage( animations[playerAction][aniIndex],
                (int) (hitbox.x -xDrawOffset),
                (int) (hitbox.y -yDrawOffset),
                width, height, null);
        drawHitbox(g);
    }


    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void updatePosition() {
        moving = false;

        if (!left && !right && !up && !down)
            return;
        float xSpeed = 0, ySpeed = 0;

        if (left && !right) {
            xSpeed -= playerSpeed;
        } else if (right && !left) {
            xSpeed += playerSpeed;
        }

        if (up && !down) {
            ySpeed -= playerSpeed;
        } else if (down && !up) {
            ySpeed += playerSpeed;
        }

//        if(CanMoveHere(x+xSpeed,y+ySpeed,width,height,lvlData)) {
//            this.x += xSpeed;
//            this.y += ySpeed;
//            moving = true;
//        }

        if(CanMoveHere(hitbox.x+xSpeed, hitbox.y+ySpeed,
                hitbox.width, hitbox.height,
                lvlData)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        }

    }

    private void setAnimation() {
        int startAni = playerAction;
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        if (attacking) {
            playerAction = ATTACK_1;
        }
        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
