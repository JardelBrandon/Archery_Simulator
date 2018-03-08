package MainJogo;

import serial.Supervisorio;
import serial.Protocolo;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameContainer extends JFrame {    
    public GameContainer(){        
        add(new Campo());        
        setSize(1425,629);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }

    public static void main(String[] args) {
        
        /*new GameContainer();
        
        Supervisorio supervisorio = new Supervisorio();
        
        Protocolo protocolo = new Protocolo();
        
        SimpleThread thread1 = new SimpleThread(protocolo.getRotaryEncoder());
        
        thread1.start();*/
        
	
    }    
}

    

