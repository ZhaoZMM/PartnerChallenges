import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private final double MOVE_AMT = 1.2;
    private BufferedImage right;
    private BufferedImage left;
    private BufferedImage right2;
    private BufferedImage left2;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private int score;
    private String name;

    public Player(String leftImg, String rightImg, String name) {
        this.name = name;
        facingRight = true;
        xCoord = 50; // starting position is (50, 435), right on top of ground
        yCoord = 435;
        score = 0;
        try {
            left = ImageIO.read(new File(leftImg));
            right = ImageIO.read(new File(rightImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            left2 = ImageIO.read(new File("src/mariofrogleft.png"));
            right2 = ImageIO.read(new File("src/mariofrogright.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getxCoord() {
        return (int) xCoord;
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int s) { score = s; }

    public String getName() {
        return name;
    }

    public void faceRight() {
        facingRight = true;
    }

    public void faceLeft() {
        facingRight = false;
    }

    public void moveRight() {
        if(!GraphicsPanel.getPauseGame()) {
            if (xCoord + MOVE_AMT <= 920) {
                xCoord += MOVE_AMT;
            }
        }
    }

    public void moveLeft() {
        if(!GraphicsPanel.getPauseGame()) {
            if (xCoord - MOVE_AMT >= 0) {
                xCoord -= MOVE_AMT;
            }
        }
    }

    public void moveUp() {
        if(!GraphicsPanel.getPauseGame()) {
            if (yCoord - MOVE_AMT >= 0) {
                yCoord -= MOVE_AMT;
            }
        }
    }

    public void moveDown() {
        if(!GraphicsPanel.getPauseGame()) {
            if (yCoord + MOVE_AMT <= 435) {
                yCoord += MOVE_AMT;
            }
        }
    }

    public void turn() {
        if(!GraphicsPanel.getPauseGame()) {
            if (facingRight) {
                faceLeft();
            } else {
                faceRight();
            }
        }
    }

    public void collectCoin() {
        score++;
    }

    public BufferedImage getPlayerImage() {
        if (score < 10) {
            if (facingRight) {
                return right;
            } else {
                return left;
            }
        }else{
            if (facingRight) {
                return right2;
            } else {
                return left2;
            }
        }
    }

    // we use a "bounding Rectangle" for detecting collision
    public Rectangle playerRect() {
        int imageHeight = getPlayerImage().getHeight();
        int imageWidth = getPlayerImage().getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }
}
