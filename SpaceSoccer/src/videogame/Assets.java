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
    public static BufferedImage player;     // to store the bar image
    public static BufferedImage ball;     // to store the ball image
    public static BufferedImage enemy;     // to store the ball image
    public static BufferedImage pause; // to store background image
    public static BufferedImage spritesBall; // to set the sprite of the ball
    public static BufferedImage moveBall[]; //moving the ball
    
    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/field.jpg");
        player = ImageLoader.loadImage("/images/player.png");
        spritesBall = ImageLoader.loadImage("/images/ball.png");
        ball = ImageLoader.loadImage("/images/ball.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        
        SpriteSheet spritesheetBall = new SpriteSheet(spritesBall);

        moveBall = new BufferedImage[7];
        
         moveBall[0] = spritesheetBall.crop(0, 0, 64, 64);
         moveBall[1] = spritesheetBall.crop(64, 0, 64, 64);
         moveBall[2] = spritesheetBall.crop(0, 64, 64, 64);
         moveBall[3] = spritesheetBall.crop(64, 64, 64, 64);
         moveBall[4] = spritesheetBall.crop(0, 128, 64, 64);
         moveBall[5] = spritesheetBall.crop(64, 128, 64, 64);
         moveBall[6] = spritesheetBall.crop(0, 192, 64, 64);
         
/*         
         for(int i = 0; i<4; i++){
            moveBall[i] = spritesheetBall.crop(i*64, 0, 64, 64);
        }
*/
    }
}
