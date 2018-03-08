package MainJogo;

import serial.Supervisorio;
import serial.Protocolo;
import javax.swing.*;
import java.awt.*;

public class Flecha {
    Supervisorio supervisorio = new Supervisorio();        
    Protocolo protocolo = new Protocolo();
    
    private Image imagem, imagem2;
    private int x, y;
    private int altura, largura;
    //private int altura_baixo, largura_baixo;
    private boolean isVisible;
    private static final int LARGURA_TELA = 1425;
    private static final int ALTURA_TELA = 629;    
    private double contador_forca;       

    public Flecha(int x, int y, int forca){

        this.x = x;
        this.y = y;
        this.contador_forca = forca;
        

        ImageIcon flecha = new ImageIcon("C:/Users/jarde/OneDrive/Documentos/GitHub/Archery_Simulator/Archery_Simulator/src/MainJogo/flecha.png");
        imagem = flecha.getImage();        
        
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);        

        isVisible = true;

    }

   
    public void mexer(){        
        
        //SimpleThread thread = new SimpleThread(protocolo.getRotaryEncoder()); 
        
        
        contador_forca = Double.parseDouble(protocolo.getRotaryEncoder());
        
        
        System.out.println(contador_forca);
             
        
    	/*if(contador_forca > 10) {
            contador_forca = 10;
    	}*/
        this.x += contador_forca;
        //System.out.println(contador_forca);
        if(this.x > LARGURA_TELA){
            isVisible = false;
        }        
    }    

    public boolean isVisible(){
        return isVisible;
    }
    public void setVisible(boolean isVisible){
        this.isVisible = isVisible;
    }
    public Image getImagem(){
        return imagem;
    }
    public Image getImagem2() {
    	return imagem2;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public double getContadorForca() {
    	return contador_forca;
    }
    
}