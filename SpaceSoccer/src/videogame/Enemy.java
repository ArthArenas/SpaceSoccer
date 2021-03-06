/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Enemy extends Item{

    private Game game; // Reference to the game
    private int score; // The vallue for winning to this enemy
    private static int direction; // The direction to move
    private boolean front; // Determines if the enemy can shoot
    private int type; // Represents the shape of the enemy
    private Animation animationEnemy; // Adding animation to the object
    private Animation animationEnemy2; // Adding animation to the object
    private Animation animationEnemy3; // Adding animation to the object
    
    /**
     * Constructor of the enemy
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     * @param width the width of the object
     * @param height the height of the object
     * @param score the amount of times it has to be hit to be destroyed
     * @param type the type of character to display
     * @param front determinate the if the enemy can shoot
     * @param game the reference to the game
     */
    public Enemy(int x, int y, int width, int height, int score, int type, boolean front, Game game) {
        super(x, y, width, height);
        this.front = front;
        this.game = game;
        this.score = score;
        this.type = type;
        this.animationEnemy = new Animation(Assets.enemyCut, 800);
        this.animationEnemy2 = new Animation(Assets.enemyCut2, 800);
        this.animationEnemy3 = new Animation(Assets.enemyCut3, 800);

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
    
    /**
     * Getter of the type
     * @return the type of the object
     */    
    public int getType() {
        return type;
    }

    /**
     * Setter for the type
     * @param type the typ of the enemy
     */
    public void setType(int type) {
        this.type = type;
    }

     /**
     * Getter of the direction
     * @return the direction of the object
     */
    public static int getDirection() {
        return direction;
    }

    /**
     * Setter for the direction
     * @param direction the sdirection to move
     */
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
    
    /**
     * Updates the attributes of the enemy
     */
    @Override
    public void tick() {
        this.animationEnemy.tick();
        this.animationEnemy2.tick();
        this.animationEnemy3.tick();
        
        if(isFront()){
            int shootingCoeff = (int)(Math.random() * 400.0);
            if(shootingCoeff == 0){
                // fires a ball
                game.addBullet(getX(), getY() + getHeight() + 3, 20, 20, true);
            }
        }

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
        
        switch (this.getType()) {
            case 1:
                g.drawImage(animationEnemy.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 2:
                g.drawImage(animationEnemy2.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 3:
                g.drawImage(animationEnemy3.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            default:
                break;
        }
    }
}