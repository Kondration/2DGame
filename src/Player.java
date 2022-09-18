import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler handler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.handler = keyHandler;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
        setDefaultValues();
        getPlayerImage();
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = gamePanel.tileSize - 16;
        solidArea.height = gamePanel.tileSize - 16;
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoUp2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoDown2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("res/PlayerGoRight2.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void setDefaultValues() {
        worldX = 12 * gamePanel.tileSize;
        worldY = 5 * gamePanel.tileSize;
        speed = 4;
        direction = "down";
    }
    public void update() {
        if(handler.upPressed || handler.downPressed || handler.leftPressed || handler.rightPressed) {
            if(handler.upPressed) {
                direction = "up";

            } else if(handler.downPressed) {
                direction = "down";

            } else if(handler.leftPressed) {
                direction = "left";

            } else if(handler.rightPressed) {
                direction = "right";

            }

            collisionOn = false;
            gamePanel.cChecker.checkTile(this);

            if(!collisionOn) {
                switch (direction) {
                    case ("up") -> worldY -= speed;
                    case ("down") -> worldY += speed;
                    case ("left") -> worldX -= speed;
                    case ("right") -> worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case ("up") -> {
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
            }
            case ("down") -> {
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
            }
            case ("left") -> {
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
            }
            case ("right") -> {
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
