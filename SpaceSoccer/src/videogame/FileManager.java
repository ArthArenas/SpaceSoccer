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
    
        // define objects
        PrintWriter printWriter;
        try{
            // creating a file
            printWriter = new PrintWriter(new FileWriter(getFileName()));
            printWriter.close();
            // saving the data
        } catch(IOException ioe){
            System.out.println("Your hard disk is full" + ioe.toString());
            return false;
        }
        return true;
    }
        String line;
        try{
            FileReader fileReader = new FileReader(getFileName());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            }
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
