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
 *
 * @author sergiosanchez
 */
public class TextLoader extends JPanel {
    
    private int score;
    private int lives;
    private Game game;
    
    public TextLoader(int score, int lives, Game game){
        this.game = game;
        this.score = score;
        this.lives = lives;
        int fontSize = 26;
//        this.game.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
//        this.game.setColor(Color.white);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    
  
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);      
      g.drawString(String.valueOf(game.getScore()), 30 , 30);
      g.drawString(String.valueOf(game.getLives()), game.getWidth()-60, 30);
      
    }
  
    public void render(Graphics g){
        paintComponent(g);
    }
  
}
