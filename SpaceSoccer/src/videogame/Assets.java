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
<<<<<<< HEAD
    public static BufferedImage player;     // to store the bar image
    public static BufferedImage ball;     // to store the ball image
    public static BufferedImage enemy;     // to store the ball image
    public static BufferedImage pause; // to store background image
    public static BufferedImage spritesBall; // to set the sprite of the ball
    public static BufferedImage moveBall[]; //moving the ball
    
=======
>>>>>>> 1728f3688dd6b82c3bf4d4ad2a091103ebc25276

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/field.jpg");
<<<<<<< HEAD
        player = ImageLoader.loadImage("/images/player.png");
        spritesBall = ImageLoader.loadImage("/images/ball.png");
        ball = ImageLoader.loadImage("/images/ball.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        
        SpriteSheet spritesheetBall = new SpriteSheet(spritesBall);

        moveBall = new BufferedImage[2];
        
        for(int i = 0; i<2; i++){
            moveBall[i] = spritesheetBall.crop(i*64, 0, 64, 64);
        }
    }
}
=======
    }
}
>>>>>>> 1728f3688dd6b82c3bf4d4ad2a091103ebc25276
