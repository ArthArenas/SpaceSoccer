/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class TextLoader extends JPanel {
    
    private Game game; // Reference to the game
    
    
    /**
     * Constructor of the enemy
     * @param game the reference to the game
     */
    public TextLoader(Game game){
        this.game = game;
    }
        
    /**
     * Paints the score and lives
     * @param g the grphics to paint the player
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);    
      int fontSize = 26;
      g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
      g.setColor(Color.white);

      g.drawString(String.valueOf(game.getScore()), 30 , 30);
      g.drawString(String.valueOf(game.getLives()), game.getWidth()-60, 30);
      
    }
  
    /**
     * Paints the text loader
     * @param g the grphics to paint the player
     */
    public void render(Graphics g){
        paintComponent(g);
    }
  
}
