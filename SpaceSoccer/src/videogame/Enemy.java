/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Enemy extends Item{

    private Game game;
    private int score;
    private static int direction;
    private boolean front; // Determines if the enemy can shoot
    private int type; // Represents the shape of the enemy
    
    /**
     * Constructor of the enemy
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     * @param width the width of the object
     * @param height the height of the object
     * @param score the amount of times it has to be hit to be destroyed
     * @param game the reference to the game
     */
<<<<<<< HEAD
    public Enemy(int x, int y, int width, int height, int type , Game game) {
        super(x, y, width, height);
        this.game = game;
        this.type = type;
=======
    public Enemy(int x, int y, int width, int height, int score, Game game) {
        super(x, y, width, height);
        game = game;
        score = score;
>>>>>>> 1728f3688dd6b82c3bf4d4ad2a091103ebc25276
        front = false;
    }

    /**
     * Getter of the score
     * @return the score of the object
     */
    public int getScore() {
        return score;
    }
    
     /**
     * Getter for the front
     * @return the status of an enemy to
     */    
    public boolean isFront() {
        return front;
    }
   
    public static int getDirection() {
        return direction;
    }

    public static void setDirection(int direction) {
        Enemy.direction = direction;
    }
    
    /**
     * Setter for the front
     * @param front the status of the enemy to shot
     */
    public void setFront(boolean front) {
        this.front = front;
    }

    /**
     * Setter of the score
     * @param score the score of the object
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getScore()
    {
        return type*15;
    }
    
    /**
     * Updates the attributes of the enemy
     */
    @Override
    public void tick() {
        if(getDirection() == 0){
            setY(getY() + 10);
        }
        else{
            setX(getX() + 3 * getDirection());
        }
    }

    /**
     * Paints the enemy
     * @param g the graphics to paint the enemy
     */
    @Override
    public void render(Graphics g) {
<<<<<<< HEAD
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
=======
        g.setColor(Color.blue);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        /*
        if(this.getPower()==3){
            g.drawImage(Assets.brick1, getX(), getY(), getWidth(), getHeight(), null);
        }else if (this.getPower() == 2){
            g.drawImage(Assets.brick2, getX(), getY(), getWidth(), getHeight(), null);
        }else if (this.getPower() == 1){
            g.drawImage(Assets.brick3, getX(), getY(), getWidth(), getHeight(), null);            
        }*/
>>>>>>> 1728f3688dd6b82c3bf4d4ad2a091103ebc25276
    }
}