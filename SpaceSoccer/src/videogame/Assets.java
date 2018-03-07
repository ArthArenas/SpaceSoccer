/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author arena
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage pauseBackground; // to store background image
    public static BufferedImage player;     // to store the bar image
    public static BufferedImage enemy;     // to store the ball image
    public static BufferedImage enemy2;     // to store the ball image
    public static BufferedImage enemy3;     // to store the ball image
    public static BufferedImage pause; // to store background image
    public static BufferedImage spritesBall; // to set the sprite of the ball
    public static BufferedImage moveBall[]; //moving the ball
    public static BufferedImage playerCut[]; //player sheet
    public static BufferedImage enemyCut[]; //player sheet
    public static BufferedImage enemyCut2[]; //player sheet
    public static BufferedImage enemyCut3[]; //player sheet
    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/field.jpg");
        pauseBackground = ImageLoader.loadImage("/images/player.png");
        player = ImageLoader.loadImage("/images/player.png");
        spritesBall = ImageLoader.loadImage("/images/ball.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        enemy2 = ImageLoader.loadImage("/images/enemy2.png");
        enemy3 = ImageLoader.loadImage("/images/enemy3.png");
        
        SpriteSheet spritesheetBall = new SpriteSheet(spritesBall);
        SpriteSheet spritesheetPlayer = new SpriteSheet(player);
        SpriteSheet spritesheetEnemy = new SpriteSheet(enemy);
        SpriteSheet spritesheetEnemy2 = new SpriteSheet(enemy2);
        SpriteSheet spritesheetEnemy3 = new SpriteSheet(enemy3);

        moveBall = new BufferedImage[7];
        
        moveBall[0] = spritesheetBall.crop(0, 0, 64, 64);
        moveBall[1] = spritesheetBall.crop(64, 0, 64, 64);
        moveBall[2] = spritesheetBall.crop(0, 64, 64, 64);
        moveBall[3] = spritesheetBall.crop(64, 64, 64, 64);
        moveBall[4] = spritesheetBall.crop(0, 128, 64, 64);
        moveBall[5] = spritesheetBall.crop(64, 128, 64, 64);
        moveBall[6] = spritesheetBall.crop(0, 192, 64, 64);

        playerCut = new BufferedImage[3];

        playerCut[0] = spritesheetPlayer.crop(1, 0, 31, 31);
        playerCut[1] = spritesheetPlayer.crop(32, 0, 32, 32);
        playerCut[2] = spritesheetPlayer.crop(288, 128, 32, 32);
        
        enemyCut = new BufferedImage[2];

        enemyCut[0] = spritesheetEnemy.crop(608, 128, 31, 31);
        enemyCut[1] = spritesheetEnemy.crop(576, 128, 32, 32);

        enemyCut2 = new BufferedImage[2];

        enemyCut2[0] = spritesheetEnemy2.crop(608, 128, 31, 31);
        enemyCut2[1] = spritesheetEnemy2.crop(576, 128, 32, 32);

        enemyCut3 = new BufferedImage[2];

        enemyCut3[0] = spritesheetEnemy3.crop(608, 128, 31, 31);
        enemyCut3[1] = spritesheetEnemy3.crop(576, 128, 32, 32);

    }
}
