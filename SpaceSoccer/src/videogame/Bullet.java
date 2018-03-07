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
 * @author arena
 */
public class Bullet extends Item{

    private Game game;  // Reference to the game
    private boolean falling;  // The direction of the bullet
    private Animation animationball;

    
    /**
     * Constructor of the bar
     * @param x the <b>x</b> position of the bullet
     * @param y the <b>y</b> position of the bullet
     * @param width the width of the bullet
     * @param height the height of the bullet
     * @param falling the direction of the bullet
     * @param game the copy of the game
     */
    public Bullet(int x, int y, int width, int height, boolean falling, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.falling = falling;
        this.animationball = new Animation(Assets.moveBall, 100);

    }

    /**
     * Setter for the direction
     * @param falling the direction of the bullet
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * Getter for the direction
     * @return the direction of the bullet
     */
    public boolean isFalling() {
        return falling;
    }

    /**
     * Updates the attributes of the bullet
     */
    @Override
    public void tick() {
        this.animationball.tick();

        if(isFalling()){
            setY(getY() + 6);
        }
        else{
            setY(getY() - 6);
        }
    }

    /**
     * Paints the bullet
     * @param g the graphics to paint the bullet
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(animationball.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        //g.setColor(Color.blue);
        //g.fillRect(getX(), getY(), getWidth(), getHeight());
        // render shifting between two costumes/versions
        // g.drawImage(Assets.bullet, getX(), getY(), getWidth(), getHeight(), null);
    }
}
