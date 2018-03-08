/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supervisorio;

import Bowman.Bowman;
import serial.SerialRxTx;
import serial.Protocolo;

/**
 *
 * @author jarde
 */
public class Supervisorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SerialRxTx serial = new SerialRxTx();
        Bowman game = new Bowman();

        if (serial.iniciaSerial()) {
            
            
            while (true) {
                Thread thread = new Thread(game);
                thread.start();
                System.out.println(Protocolo.getRotaryEncoder());
                System.out.println(Protocolo.getGiroscopio());
            }
        } else {
            
        }
    }
    
}
