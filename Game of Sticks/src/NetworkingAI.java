/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author mhrcek
 */
public abstract class NetworkingAI implements Runnable{
    
    private static final int PORT = 80;
    private String host;
    
    private Socket socket;
    
    private volatile boolean matchStillActive;
    private volatile int maxSticksTaken;
    private volatile int sticksRemaining;
    private volatile boolean hasWonMatch;
    
    /**
     * 
     * @param host The ip of the server.
     */
    public NetworkingAI(String host){
        this.host = host;
    }
    
    private void connectToServer(){
        
        try{
            socket = new Socket(host, PORT);
            
            Thread t = new Thread(this);
        } catch(IOException e){
            System.err.println("Unable to connect to server!");
        }
        
    }
    /**
     * Get the max number of sticks you may take.
     * @return Maximum number of sticks that may be taken.
     */
    public synchronized int getMaxSticksTaken(){
        return maxSticksTaken;
    }
    
    /**
     * Get the number of sticks remaining.
     * @return Number of sticks the server has.
     */
    public synchronized int getSticksRemaining(){
        return sticksRemaining;
    }
    
    /**
     * Take some sticks from the server.
     * @param numSticks Number of sticks to take.
     * @return If the sticks could be taken.
     */
    public synchronized boolean takeSticks(int numSticks){
        return false;
    }
    
    /**
     * Determines if the player won the game.
     * @return If the player has won.
     */
    public synchronized boolean wonMatch(){
        return hasWonMatch;
    }
    
    /**
     * Runs when the game finishes. This is where you can tell your AI if it
     * won the game.
     */
    public abstract void gameOver();
    
    /**
     * AI game playing logic goes here.
     */
    public abstract void playGame();
    
    /**
     * Main loop
     */
    @Override
    public void run(){
        while(true){
            waitForMatch();
            requestMatchData();
            while(requestIsMatchStillActive()){
                requestMatchDataUpdate();
                playGame();
            }
            requestHasWonMatch();
            gameOver();
        }
    }
    
    private synchronized void requestHasWonMatch(){
        
    }
    
    private synchronized void waitForMatch(){
        
    }
    
    private synchronized void requestMatchData(){
        
    }
    
    private synchronized void requestMatchDataUpdate(){
        
    }
    
    private synchronized boolean requestIsMatchStillActive(){
        return false;
    }
    
}
