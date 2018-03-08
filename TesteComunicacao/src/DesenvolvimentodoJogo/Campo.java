package DesenvolvimentodoJogo;


import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import serial.Protocolo;

public class Campo extends JPanel implements ActionListener {
	
    private Image campo;
    private Arco arco;
    private Timer timer;
    private Image arco2;
	
	public Campo() {
            setFocusable(true);
            setDoubleBuffered(true);
            addKeyListener(new TecladoAdapter());

            ImageIcon image_fundo = new ImageIcon("C:\\Users\\jarde\\OneDrive\\Documentos\\GitHub\\Archery_Simulator\\TesteComunicacao\\src\\DesenvolvimentodoJogo\\images\\camp.jpg");
            campo = image_fundo.getImage();

            arco = new Arco();

            timer = new Timer(1, this);
            timer.start();
	}
	
	public void paint(Graphics g){            
            Graphics2D graficos = (Graphics2D) g;
            
            graficos.drawImage(campo, 0, 0, null);
            
            graficos.setColor(Color.BLACK);            
            graficos.drawString("Rotary Encoder:" + Protocolo.getRotaryEncoder(), 5, 15);
            graficos.drawString("Giroscopio:" + Protocolo.getGiroscopio(), 20, 30);
            
            if(Protocolo.getGiroscopio() != null){                
                graficos.rotate(Math.toRadians(Double.parseDouble(Protocolo.getGiroscopio())));                
                graficos.drawImage(arco.getImagemAcao(), (int)arco.getX(), (int)arco.getY(), null);
            }
                       
            List<Flecha> flechas = arco.getFlechas();
            for(int i = 0; i < flechas.size(); i++){
                Flecha f = (Flecha) flechas.get(i);
                graficos.drawImage(f.getImagem(), (int)f.getX(), (int)f.getY(), this);
            }

            List<FlechaCima> flechaCimas = arco.getFlechasCima();
            for(int i = 0; i < flechaCimas.size(); i++){
                FlechaCima fc = (FlechaCima) flechaCimas.get(i);
                graficos.drawImage(fc.getImagem(), (int)fc.getX(), (int)fc.getY(), this);
            }
            
            

            g.dispose();
            
    }        
    

    public void actionPerformed(ActionEvent arg0){

        List<FlechaCima> flechaCimas = arco.getFlechasCima();

        for(int i = 0; i < flechaCimas.size(); i++){
            FlechaCima fc = (FlechaCima) flechaCimas.get(i);
            if(fc.isVisibleCima()){
                fc.mexerCima();
            }
            else{
                flechaCimas.remove(i);
            }
        }

        List<Flecha> flechas = arco.getFlechas();

        for(int i = 0; i < flechas.size(); i++){
            Flecha f = (Flecha) flechas.get(i);
            if(f.isVisible()){
                f.mexer();                
            }
            else{
                flechas.remove(i);
            }
        }
        repaint();
    }   
    
    
    private class TecladoAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            arco.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            arco.keyReleased(e);
        }
    }
}