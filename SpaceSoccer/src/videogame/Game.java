/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.util.Pair;

/**
 *
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez
 */
public class Game implements Runnable {
    private BufferStrategy bs;          // to have several buffers when displaying
    private Graphics g;                 // to paint objects
    private Display display;            // to display in the game
    String title;                       // title of the window
    private int width;                  // width of the window
    private int height;                 // height of the window
    private Thread thread;              // thread to create the game
    private boolean running;            // to set the game
    private boolean paused;             // pause status
    private boolean death;              // death status
    private Player player;              // the player of the game
    private ArrayList<Bullet> bullets;  // current fired bullets in the air 
    private ArrayList<Enemy> enemies;   // enemies of the player
    private ArrayList<Barrier> barriers;// protection barriers
    private KeyManager keyManager;      // to manage the keyboard
    private int lives;                  // amount of lives left
    private int score;                  // score of the player
    final private int LIVES;            // initial amount of lives
    // store pairs of IDs and times of activeness to represent active perks
    private ArrayList<Pair<Integer, Integer>> activePerks;
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        barriers = new ArrayList<Barrier>();
        this.title = title;
        this.width = width;
        this.height = height;
        LIVES = 3;
        lives = LIVES;
        score = 0;
        paused = false;
        running = false;
        death = false;
        keyManager = new KeyManager();
    }
    
    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the amount of lives left 
     * @return the amount of lives left
     */
    public int getLives() {
        return lives;
    }

    /**
     * Getter for the death status of the game
     * @return the death status of the game
     */
    public boolean isDeath() {
        return death;
    }

    /**
     * Sets the amount of lives left 
     * @param lives the amount of lives left
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Setter for the death status of the game
     * @param death the death status of the game
     */
    public void setDeath(boolean death) {
        this.death = death;
    }

    /**
     * Setter for the running status of the game
     * @param running the running status of the game
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    /**
     * Resets initial positions of the elements of the game
     */
    public void restart(){
         //
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         // Assets.init();
         int width_enemy = getWidth() / 10 - 6;
         int height_enemy = getHeight() / 3 / 5  - 10;
         for (int i = 0; i < 10; i++) {
             for (int j = 0; j < 5; j++) {
                 Enemy enemy = new Enemy(i * (width_enemy + 3) + 15 , 
                         j * (height_enemy + 5) + 15 , width_enemy, height_enemy, 100, this);
                 enemies.add(enemy);
             }
         }
         display.getJframe().addKeyListener(keyManager);
    }
    
    /**
     * Runs the game
     */
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                if(!death){
                    tick();
                }
                else{
                    keyManager.tick();
                    if(getKeyManager().isRestart()){
                        setDeath(false);
                        restart();
                    }
                }
                render();
                delta --;
            }
        }
        System.out.println("your score was: " + score); // this must be displayed instead
        render(); // in case we want to display a losing or winning picture
        // stop(); we should use something like thread.sleep() and then close
    }

    /**
     * Getter for the key manager
     * @return the key manager of the game
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    /**
     * Updates the elements of the game
     */
    private void tick() {
        keyManager.tick();
        if(getKeyManager().isPause()){
            getKeyManager().setPause(false);
            paused = !paused;
        }
        if(!paused){
            // ticking the player
            player.tick();
            // ticking the enemies
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).tick();
            }
            // ticking the bullets
            for(int i = 0; i < bullets.size(); i++){
                bullets.get(i).tick();
            }
            // ticking the barriers
            for(int i = 0; i < barriers.size(); i++){
                barriers.get(i).tick();
            }
            // checking collisions of the bullets against barriers, the player
            for (int i = 0; i < bullets.size(); i++) {
                Bullet b = bullets.get(i);
                // check collision with barriers
                for(int j = 0; j < barriers.size(); j++){
                    Barrier barrier = barriers.get(j);
                    if(b.intersects(barrier)){
                        // destroy bullet and lower the power of the barrier
                        
                        // destroy the barrier if necessary
                    }
                }
                // check collision with enemies
                for(int j = 0; j < enemies.size(); j++){
                    Enemy enemy = enemies.get(j);
                    if(b.intersects(enemy)){
                        // destroy enemy and bullet
                        
                        // add score
                    }
                }
                // check collision with the player
                if(b.intersects(player)){
                    // destroy bullet
                    
                    // lose a life
                    
                    // set death to true
                    
                }
            }
        }
    }
    
    /**
     * Paints the elements of the game
     */
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            // render the elements of the game
            if(running){
                if(paused){
                    // g.drawImage(Assets.pauseBackground, 0, 0, width, height, null);
                }
                else{
                    // g.drawImage(Assets.background, 0, 0, width, height, null);
                    // render the player
                    player.render(g);
                    // render all the enemies
                    for(int i = 0; i < enemies.size(); i++){
                        enemies.get(i).render(g);
                    }
                    // render all the bullets
                    for (int i = 0; i < bullets.size(); i++){
                        bullets.get(i).render(g);
                    }
                    // render all the barriers
                    for(int i = 0; i < barriers.size(); i++){
                        barriers.get(i).render(g);
                    }
                }
            }
            else{
                // render lose or win screen
                if(enemies.size() == 0){
                    // g.drawImage(Assets.winBackground, 0, 0, width, height, null);
                }
                else{
                    // g.drawImage(Assets.loseBackground, 0, 0, width, height, null);
                }
            }
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
}