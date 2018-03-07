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

    private Game game;  // Reference to the game
    private boolean front; // Determinate if the enemy can shot
    private int type; // Represent the shape of the enemy
    
    /**
     * Constructor of the bar
     * @param x the <b>x</b> position of the player
     * @param y the <b>y</b> position of the player
     * @param width the width of the player
     * @param height the height of the player
     * @param game the copy of the game
     */
    public Enemy(int x, int y, int width, int height, int type , Game game) {
        super(x, y, width, height);
        this.game = game;
        this.type = type;
        front = false;
    }

     /**
     * Getter for the front
     * @return the status of an enemy to
     */    
    public boolean isFront() {
        return front;
    }

    
    /**
     * Setter for the front
     * @param front the status of the enemy to shot
     */
    public void setFront(boolean front) {
        this.front = front;
    }
    
    public int getScore()
    {
        return type*15;
    }
    
    /**
     * Update the sttributes of the bar
     */
    @Override
    public void tick() {
    }

    /**
     * Paints the bar
     * @param g the grphics to paint the bar
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}