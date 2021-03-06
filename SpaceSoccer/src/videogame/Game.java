/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Game implements Runnable{
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
    private FileManager fileManager;    // to load the file manager
    private int lives;                  // amount of lives left
    private int score;                  // score of the player
    private TextLoader textloader;      // to print text
    final private int LIVES;            // initial amount of lives
    
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
        textloader = new TextLoader(this);
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

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Getter for the death status of the game
     * @return the death status of the game
     */
    public boolean isDeath() {
        return death;
    }
    
    
    /**
     * Getter for the status of the bullet
     * @return the status of the bullet
     */
    public boolean isBulletInTheAir() {
        for(int i = 0; i < bullets.size(); i++){
            if(!bullets.get(i).isFalling()){
                return true;
            }
        }
        return false;
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
    
    public void addBullet(int x, int y, int width, int height, boolean falling){
        Bullet newBullet = new Bullet(x, y, width, height, falling, this);
        bullets.add(newBullet);
    }
    
    public void loadingGame(int lives, int score, int playerPosX, int playerPosY, int bulletsSize, int[] BulletsPosX, int[] BulletsPosY, boolean[] BulletsFalling,
                     int barriersSize, int[] BarriersPosX, int[] BarriersPosY, int[] BarriersPower, int enemiesSize, int[] EnemiesPosX, int[] EnemiesPosY, int[] EnemiesType, boolean[] EnemiesFront){
        setLives(lives);
        setScore(score);
        getPlayer().setX(playerPosX);
        getPlayer().setY(playerPosY);
        bullets.clear();
        for(int i = 0; i < bulletsSize; i++){
            Bullet b = new Bullet(BulletsPosX[i], BulletsPosY[i], 20, 20, BulletsFalling[i], this);
            bullets.add(b);
        }
        barriers.clear();
        for(int i = 0; i < barriersSize; i++){
            Barrier b = new Barrier(BarriersPosX[i], BarriersPosY[i], 100, 125, BarriersPower[i], this);
            barriers.add(b);
        }
        enemies.clear();
        for(int i = 0; i < enemiesSize; i++){
            Enemy e = new Enemy(EnemiesPosX[i], EnemiesPosY[i], getHeight() / 3 / 5  + 5, getHeight() / 3 / 5  - 10, 100, EnemiesType[i], EnemiesFront[i], this);
            enemies.add(e);
        }
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         int height_enemy = getHeight() / 3 / 5  - 10;
         int width_enemy = height_enemy + 15;
         player = new Player(getWidth() / 2 - width_enemy / 2, getHeight() - 50, width_enemy, height_enemy, this);
         Enemy.setDirection(1);
         
         int separation = getWidth() / 5;
         for(int i = 1; i <= 4; i++){
             Barrier barry = new Barrier(separation * i - 50, 500, 100, 125, 8, this);
             barriers.add(barry);
         }
                
         for (int i = 0; i < 10; i++) {
             for (int j = 0; j < 5; j++) {
                 switch (j) {
                     case 0:
                         {
                             Enemy enemy = new Enemy(i * (width_enemy + 30) + 120 ,
                                     j * (height_enemy + 5) + 70 , width_enemy, height_enemy, 100, 3, false, this);
                             enemies.add(enemy);
                             break;
                         }
                     case 1:
                     case 2:                         
                         {
                             Enemy enemy = new Enemy(i * (width_enemy + 30) + 120 ,
                                     j * (height_enemy + 5) + 70 , width_enemy, height_enemy, 200, 2, false, this);
                             enemies.add(enemy);
                             break;
                         }
                     case 3:
                     case 4:
                         {
                             boolean frontFlag = (j == 4);
                             Enemy enemy = new Enemy(i * (width_enemy + 30) + 120 ,
                                     j * (height_enemy + 5) + 70 , width_enemy, height_enemy, 100, 1, frontFlag, this);
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
     * Getter for the text loader
     * @return the text loader of the game
     */    
    public TextLoader getTextLoader(){
        return textloader;
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
            if(getKeyManager().isLoad()){
                FileManager.loadFile(this);
            }
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
                if(enemies.get(i).getY()+50 == getHeight() + 50){
                    setLives(getLives() - 1);
                    // check if the player has lost
                    if(getLives() == 0){
                        setRunning(false);
                    }
                    // set death to true
                    setDeath(true);
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
            if (this.getKeyManager().space && !isBulletInTheAir()) {
                bullets.add(new Bullet(this.player.getX() + this.player.getWidth() / 2 - 10,
                    this.player.getY()-20, 20, 20, false, this));
            }
            // ticking the bullets
            for(int i = 0; i < bullets.size(); i++){
                bullets.get(i).tick();
                if(bullets.get(i).getY() + 20 <= 0){
                    bullets.remove(i);
                    i--;
                }
                else if(bullets.get(i).getY() >= getHeight()){
                    bullets.remove(i);
                    i--;
                }
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
                        }
                        j = barriers.size();
                    }
                }
                // check collision with enemies
                for(int j = 0; j < enemies.size(); j++){
                    Enemy enemy = enemies.get(j);
                    if(b.intersects(enemy)){
                        // update bullet in the air flag
                        if(!b.isFalling()){
                        }
                        // change front if neccessary
                        if(enemy.isFront()){
                            if(j - 1 >= 0 && enemies.get(j - 1).getY() < enemy.getY() && Math.abs(enemies.get(j - 1).getX() - enemy.getX()) < 20){
                                enemies.get(j - 1).setFront(true);
                            }
                        }
                        // add score
                        setScore(getScore() + enemy.getScore());
                        // destroy enemy and bullet
                        bullets.remove(i);
                        i--;
                        enemies.remove(j);
                        j = enemies.size();
                    }
                }
                // check if the player has won
                if(enemies.size() == 0){
                    setRunning(false);
                }
                // check collision with the player
                if(b.intersects(player)){
                    // destroy bullet
                    bullets.remove(i);
                    i--;
                    // lose a life
                    setLives(getLives() - 1);
                    // check if the player has lost
                    if(getLives() == 0){
                        setRunning(false);
                    }
                    // set death to true
                    setDeath(true);
                }
            }
        }
        else{
            // we can save from here
            if(getKeyManager().isSave()){
                getKeyManager().setSave(false);
                FileManager.saveFile(this);
                FileManager.loadFile(this);
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
                     g.drawImage(Assets.pause, 0, 0, width, height, null);
                }
                else if(this.isDeath()){
                    g.drawImage(Assets.restart, 0, 0, width, height, null);
                    
                }else{
                    g.drawImage(Assets.background, 0, 0, width, height, null);
                    // render the player
                    //player.render(g);
 
                    player.render(g);
                   
                    textloader.render(g);
                    
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
                    g.drawImage(Assets.win, 0, 0, width, height, null);
                }
                else{
                    g.drawImage(Assets.lose, 0, 0, getWidth() + 100, getHeight(), null);
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