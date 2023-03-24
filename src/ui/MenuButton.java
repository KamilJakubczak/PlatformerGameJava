package ui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import gamestates.Gamestate;
import utils.LoadSave;
import static utils.Constants.UI.Buttons.*;

public class MenuButton {

    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH /2 ;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mousePressed, mouseOver;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state){

        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;

        loadImg();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGHT);
    }

    private void loadImg() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i =0 ; i <imgs.length; i++) {
            imgs[i] = temp.getSubimage(i*B_WIDTH_DEFAUTL, rowIndex * B_HEIGHT_DEFAUTL,B_WIDTH_DEFAUTL,B_WIDTH_DEFAUTL);
        }            
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos-xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if(mouseOver) 
            index = 1;
        if(mousePressed) 
            index = 2;
    }

    public boolean isMouseOver() {
        return this.mouseOver;
    }
    
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }


    public boolean isMousePressed() {
        return this.mousePressed;
    }
    
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}


