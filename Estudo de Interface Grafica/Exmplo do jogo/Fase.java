package Programining3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Fase extends JPanel implements ActionListener{

    private Image fundo;
    private Nave nave;
    //atualiza a tela para cada movimento
    private Timer timer;

    public Fase(){
        //Listener que executa a ação
        addKeyListener(new TecladoAdapter());
        ImageIcon referencia = new ImageIcon("Imagens\\universo.jpeg");
        fundo = referencia.getImage();

        nave = new Nave();

        //define o tempo - em milisegundo - e a ação listener
        timer = new Timer(5,this);
        //metodo que inicia o timer
        timer.start();
    }
    //pinta a tela
    public void paint(Graphics g){
        //necessário para pintar imagens em 2D
        Graphics2D graficos = (Graphics2D) g;
        //desenha o fundo da tela
        graficos.drawImage(fundo, 0,0,null);
        //desenha a nave na tela
        graficos.drawImage(nave, nave.getX(), nave.getY(), this);

        //limpa a tela para proxima pintura
        g.dispose();
    }

    //faz a ação de mexer a nave
    public void actionPerformed(ActionEvent arg0){
        nave.mexer();
        //ação é uma repetição de imagens, onde a primeira imagem é esquecida
        // e a próxima é sobreposta
        repaint();
    }
    //pega os eventos do teclado
    private class TecladoAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            //como o objeto nave ja tem as ações keyPressed e keyReleased
            //não foi necessitado a utilização super.keyPressed
            nave.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            nave.keyReleased(e);

        }
    }

}
