package Bowman;


import serial.Protocolo;
/**
 * Main class responsible for starting the game
 * 
 * @author a-haydar
 */
public class Bowman implements Runnable{
    
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 600;
    private static final String TITLE = "Bowman v0.1";
    

    @Override
    public void run() {
        // create game & start it
        Game game = new Game(WINDOW_WIDTH, WINDOW_HEIGHT);
        // create window with this game
        Window window = new Window(WINDOW_WIDTH, WINDOW_HEIGHT, game, TITLE);
        // start game
        game.start();
        teste();
    }
    public void teste(){
        System.out.println(Protocolo.getGiroscopio());
    }
    
}
