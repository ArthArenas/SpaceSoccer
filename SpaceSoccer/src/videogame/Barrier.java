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
public class Barrier extends Item{

    private Game game;  // Reference to the game
    private int power;  // The amount of times it has to be hit to be destroyed
    
    /**
     * Constructor of the bar
     * @param x the <b>x</b> position of the player
     * @param y the <b>y</b> position of the player
     * @param width the width of the player
     * @param height the height of the player
     * @param power the amount of times it has to be hit to be destroyed
     * @param game the copy of the game
     */
    public Barrier(int x, int y, int width, int height, int power, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.power = power;
    }

    /**
     * Setter for the power
     * @param power the amount of times it has to be hit to be destroyed
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Getter for the power
     * @return the amount of times it has to be hit to be destroyed
     */
    public int getPower() {
        return power;
    }

    /**
     * Update the sttributes of the bar
     */
    @Override
    public void tick() {
        // nothing to update
    }

    /**
     * Paints the bar
     * @param g the grphics to paint the bar
     */
    @Override
    public void render(Graphics g) {
        // render depending on its power
        // g.drawImage(Assets.barrier, getX(), getY(), getWidth(), getHeight(), null);
    }
}
