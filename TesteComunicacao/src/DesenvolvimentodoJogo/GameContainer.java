package DesenvolvimentodoJogo;

import serial.Protocolo;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameContainer extends JFrame implements Runnable {
	
    public GameContainer(){
        add(new Campo());
        setSize(1425,629);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void run() {

    }

}
