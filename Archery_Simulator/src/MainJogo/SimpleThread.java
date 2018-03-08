/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainJogo;

import serial.Protocolo;
import serial.Supervisorio;

public class SimpleThread extends Thread {    
    private String dados_rotary;
    private double rotary_encoder = 0;    
    
    public SimpleThread(String str){        
        this.dados_rotary = str;        
    }  
    
    @Override
    public void run(){
        try{
            //Supervisorio supervisorio = new Supervisorio();
            Protocolo protocolo = new Protocolo();
            //rotary_encoder = Double.parseDouble(dados_rotary);
            
            System.out.println("Thread Rodando");
            
            System.out.println(dados_rotary + "funcionando");
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }    
    
    public double RotaryEncoder(){
        return rotary_encoder;
    }
}
