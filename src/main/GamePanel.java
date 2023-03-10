package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {

    private final MouseInputs mouseInputs;
    private Game game;
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
        //Fix lagging without mouse move some architecture issue
        Toolkit.getDefaultToolkit().sync();
    }





    public void updateGame() {
    }
    public Game getGame() {
        return game;
    }
 }