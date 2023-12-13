package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        direction = "down";
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void getPlayerImage() {
        try {
            up1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png"));
            up2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png"));
            down1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png"));
            down2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png"));
            left1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png"));
            left2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png"));
            right1 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png"));
            right2 =  ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed|| keyH.leftPressed || keyH.rightPressed) { // only update between the sprites if a key is actually pressed, so it isn't idle and animating
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1)
                    spriteNumber = 2;
                else
                    spriteNumber = 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) { //update the characters sprites

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1)
                    image = up1;
                else
                    image = up2;
            }
            case "down" ->  {
                if (spriteNumber == 1)
                    image = down1;
                else
                    image = down2;
            }
            case "left" ->  {
                if (spriteNumber == 1)
                    image = left1;
                else
                    image = left2;
            }
            case "right" ->  {
                if (spriteNumber == 1)
                    image = right1;
                else
                    image = right2;
            }
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); //draw image of new sprite on players new x/y and based on the default tilesize (48px)
    }
}
