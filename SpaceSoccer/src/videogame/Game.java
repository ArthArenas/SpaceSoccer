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

    public int getScore() {
        return score;
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

    public void setScore(int score) {
        this.score = score;
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
         Assets.init();
         int height_enemy = getHeight() / 3 / 5  - 10;
         int width_enemy = height_enemy + 15;
         player = new Player(getWidth() / 2 - width_enemy / 2, getHeight() - 75, width_enemy, height_enemy, this);
         Enemy.setDirection(1);
         for (int i = 0; i < 10; i++) {
             for (int j = 0; j < 5; j++) {
                 switch (j) {
                     case 0:
                         {
                             Enemy enemy = new Enemy(i * (width_enemy + 30) + 120 ,
                                     j * (height_enemy + 5) + 70 , width_enemy, height_enemy, 100, 3, this);
                             enemies.add(enemy);
                             break;
                         }
                     case 1:
                     case 2:                         
                         {
                             Enemy enemy = new Enemy(i * (width_enemy + 30) + 120 ,
                                     j * (height_enemy + 5) + 70 , width_enemy, height_enemy, 100, 2, this);
                             enemies.add(enemy);
                             break;
                         }
                     case 3:
                     case 4:
                         {
                             Enemy enemy = new Enemy(i * (width_enemy + 30) + 120 ,
                                     j * (height_enemy + 5) + 70 , width_enemy, height_enemy, 100, 1, this);
                             enemies.add(enemy);
                             break;
                         }
                     default:
                         break;
                 }
                     
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
            // ticking the enemies
            player.tick();
            // get the left-most and right-most positions of the enemies
            int left = getWidth();
            int right = 0;
            for(int i = 0; i < enemies.size(); i++){
                if(enemies.get(i).getX() < left){
                    left = enemies.get(i).getX();
                }
                if(enemies.get(i).getX() > right){
                    right = enemies.get(i).getX();
                }
            }
            if((right + 70 >= getWidth() || left - 10 <= 0) && Enemy.getDirection() != 0){
                // go down
                Enemy.setDirection(0);
            }
            else if(Enemy.getDirection() == 0){
                // we've gone down, now let's continue in our proper direction
                if(right + 70 >= getWidth()){
                    Enemy.setDirection(-1);
                }
                else if(left - 10 <= 0){
                    Enemy.setDirection(1);
                }
            }
            // tick the enemies
            for(int i = 0; i < enemies.size(); i++){
                enemies.get(i).tick();
            }
            if (this.getKeyManager().space) {
                bullets.add(new Bullet(this.player.getX() + this.player.getWidth() / 2 - 10,
                    this.player.getY()-200, 20, 20, false, this));
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
                        bullets.remove(i);
                        i--;
                        barrier.setPower(barrier.getPower() - 1);
                        // destroy the barrier if necessary
                        if(barrier.getPower() == 0){
                            barriers.remove(j);
                            j--;
                        }
                    }
                }
                // check collision with enemies
                for(int j = 0; j < enemies.size(); j++){
                    Enemy enemy = enemies.get(j);
                    if(b.intersects(enemy)){
                        // add score
                        setScore(getScore() + enemy.getScore());
                        // destroy enemy and bullet
                        bullets.remove(i);
                        i--;
                        enemies.remove(j);
                        j--;
                    }
                }
                // check collision with the player
                if(b.intersects(player)){
                    // destroy bullet
                    bullets.remove(i);
                    i--;
                    // lose a life
                    setLives(getLives() - 1);
                    // set death to true
                    setDeath(true);
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
                     g.drawImage(Assets.pauseBackground, 0, 0, width, height, null);
                }
                else{
                    g.drawImage(Assets.background, 0, 0, width, height, null);
                    // render the player
                    //player.render(g);
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