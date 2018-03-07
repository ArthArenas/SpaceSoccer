/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author arena
 */
public class FileManager {
    private static String fileName;

    public static void setFileName(String fileName) {
        FileManager.fileName = fileName;
    }

    public static String getFileName() {
        return fileName;
    }
    
    public static boolean saveFile(Game game){
        // define objects
        PrintWriter printWriter;
        try{
            // creating a file
            printWriter = new PrintWriter(new FileWriter(getFileName()));
            // save game lives and score
            printWriter.println("" + game.getLives() + " " + game.getScore());
            // save players's position (x, y)
            printWriter.println("" + game.getPlayer().getX() + " " + game.getPlayer().getY());
            // save number of bullets and bullets's position (x, y) and direction (isFalling true = 1, false = 0)
            ArrayList<Bullet> bullets = game.getBullets();
            printWriter.println("" + bullets.size());
            for(int i = 0; i < bullets.size(); i++){
                int fall;
                fall = bullets.get(i).isFalling() ? 1 : 0;
                printWriter.println("" + bullets.get(i).getX() + " " + bullets.get(i).getY() + " " + fall);
            }
            // save number of barriers and barrier's position (x, y) and power
            ArrayList<Barrier> barriers = game.getBarriers();
            printWriter.println("" + barriers.size());
            for(int i = 0; i < barriers.size(); i++){
                printWriter.println("" + barriers.get(i).getX() + " " + barriers.get(i).getY() + " " + 
                        barriers.get(i).getPower());
            }
            // save number of enemies and enemy's position (x, y) and type and front
            ArrayList<Enemy> enemies = game.getEnemies();
            printWriter.println("" + enemies.size());
            for(int i = 0; i < enemies.size(); i++){
                int front;
                front = enemies.get(i).isFront() ? 1 : 0;
                printWriter.println("" + enemies.get(i).getX() + " " + enemies.get(i).getY() + " " + enemies.get(i).getType() + " " + front);
            }
            printWriter.close();
            // saving the data
        } catch(IOException ioe){
            System.out.println("Your hard disk is full" + ioe.toString());
            return false;
        }
        return true;
    }
    public static boolean loadFile(Game game){
        String line;
        try{
            FileReader fileReader = new FileReader(getFileName());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lives, score;
            String[] firstLine = bufferedReader.readLine().split(" ");
            lives = Integer.parseInt(firstLine[0]);
            score = Integer.parseInt(firstLine[1]);
            String[] playerPos = bufferedReader.readLine().split(" ");
            int playerPosX = Integer.parseInt(playerPos[0]);
            int playerPosY = Integer.parseInt(playerPos[1]);
            
            // Read bullet info
            int bulletsSize = Integer.parseInt(bufferedReader.readLine());
            int[] BulletsPosX = new int[bulletsSize];
            int[] BulletsPosY = new int[bulletsSize];
            boolean[] BulletsFalling = new boolean[bulletsSize];
            for(int i = 0; i < bulletsSize; i++){
                String[] bulletInfo = bufferedReader.readLine().split(" ");
                BulletsPosX[i] = Integer.parseInt(bulletInfo[0]);
                BulletsPosY[i] = Integer.parseInt(bulletInfo[1]);
                BulletsFalling[i] = (Integer.parseInt(bulletInfo[2]) == 1) ? true : false;
            }
            
            // Read barrier info
            int barriersSize = Integer.parseInt(bufferedReader.readLine());
            int[] BarriersPosX = new int[barriersSize];
            int[] BarriersPosY = new int[barriersSize];
            int[] BarriersPower = new int[barriersSize];
            for(int i = 0; i < barriersSize; i++){
                String[] barrierInfo = bufferedReader.readLine().split(" ");
                BulletsPosX[i] = Integer.parseInt(barrierInfo[0]);
                BulletsPosY[i] = Integer.parseInt(barrierInfo[1]);
                BarriersPower[i] = Integer.parseInt(barrierInfo[2]);
            }
            
            // Read enemy info
            int enemiesSize = Integer.parseInt(bufferedReader.readLine());
            int[] EnemiesPosX = new int[enemiesSize];
            int[] EnemiesPosY = new int[enemiesSize];
            int[] EnemiesType = new int[enemiesSize];
            boolean[] EnemiesFront = new boolean[enemiesSize];
            for(int i = 0; i < enemiesSize; i++){
                String[] enemyInfo = bufferedReader.readLine().split(" ");
                EnemiesPosX[i] = Integer.parseInt(enemyInfo[0]);
                EnemiesPosY[i] = Integer.parseInt(enemyInfo[1]);
                EnemiesType[i] = Integer.parseInt(enemyInfo[2]);
                EnemiesFront[i] = (Integer.parseInt(enemyInfo[3]) == 1) ? true : false;
            }
            game.loadingGame(lives, score, playerPosX, playerPosY, bulletsSize, BulletsPosX, BulletsPosY, BulletsFalling,
                    barriersSize, BarriersPosX, BarriersPosY, BarriersPower, enemiesSize, EnemiesPosX, EnemiesPosY, EnemiesType, EnemiesFront);
            bufferedReader.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("The loading file has not been found");
            return false;
        }
        catch(IOException ioe){
            System.out.println("Error reading loading file");
            return false;
        }
        return true;
    }
}
