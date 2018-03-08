package DesenvolvimentodoJogo;

import javax.swing.*;
import java.awt.*;

public class Flecha {
	
    private Image imagem, imagem2;
    private double x, y;
    private int altura, largura;
    //private int altura_baixo, largura_baixo;
    private boolean isVisible;
    private static final int LARGURA_TELA = 1425;
    private static final int ALTURA_TELA = 629;    
    private double contador_forca;       

    public Flecha(double x, double y, double forca){

        this.x = x;
        this.y = y;
        this.contador_forca = forca;
        

        ImageIcon flecha = new ImageIcon("C:\\Users\\jarde\\OneDrive\\Documentos\\GitHub\\Archery_Simulator\\TesteComunicacao\\src\\DesenvolvimentodoJogo\\images\\flecha.png");
        imagem = flecha.getImage();
        
        /*ImageIcon flecha_baixo = new ImageIcon("images\\Flecha_baixo.png");
        imagem2 = flecha_baixo.getImage();*/

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);        

        isVisible = true;

    }    
    
    public void mexer(){
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
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    /*public int getContadorForca() {
    	return contador_forca;
    }*/

}