/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Player extends Item{

    private final Game game;  // Reference to the game
    private final Animation animationPlayer;  // Adding animation to the object

    
    /**
     * Constructor of the player
     * @param x the <b>x</b> position of the player
     * @param y the <b>y</b> position of the player
     * @param width the width of the player
     * @param height the height of the player
     * @param game the copy of the game
     */
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.animationPlayer = new Animation(Assets.playerCut, 300);

    }

    /**
     * Update the sttributes of the player
     */
    @Override
    public void tick() {  
        this.animationPlayer.tick();
        // moving player depending on keys <-  ->
        if (game.getKeyManager().left) {
           setX(getX() - 6);
        }
        if (game.getKeyManager().right) {
           setX(getX() + 6);
        }
        // collision with walls
        if (getX() + 100 >= game.getWidth()) {
            setX(game.getWidth() - 100);
        }
        else if (getX() <= 0) {
            setX(0);
        }
    }

    /**
     * Paints the player
     * @param g the grphics to paint the player
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(animationPlayer.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
