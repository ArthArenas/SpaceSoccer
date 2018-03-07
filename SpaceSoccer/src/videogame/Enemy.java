/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author arena
 */
public class Enemy extends Item{

    private Game game;
    private int score;
    
    /**
     * Constructor of the enemy
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     * @param width the width of the object
     * @param height the height of the object
     * @param score the amount of times it has to be hit to be destroyed
     * @param game the reference to the game
     */
    public Enemy(int x, int y, int width, int height, int score, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.score = score;
    }

    /**
     * Getter of the score
     * @return the score of the object
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter of the score
     * @param score the score of the object
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * Update the sttributes of the enemy
     */
    @Override
    public void tick() {
        //
    }

    /**
     * Paints the enemy
     * @param g the graphics to paint the enemy
     */
    @Override
    public void render(Graphics g) {
        /*
        if(this.getPower()==3){
            g.drawImage(Assets.brick1, getX(), getY(), getWidth(), getHeight(), null);
        }else if (this.getPower() == 2){
            g.drawImage(Assets.brick2, getX(), getY(), getWidth(), getHeight(), null);
        }else if (this.getPower() == 1){
            g.drawImage(Assets.brick3, getX(), getY(), getWidth(), getHeight(), null);            
        }*/
    }
}