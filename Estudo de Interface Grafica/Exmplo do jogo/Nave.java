package Programining3D;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Nave {
    //define as coordenadas da nave
    private int x, y;
    //define as coordenadas do movimento da nave
    private int dx, dy;
    private Image imagem;

    public Nave(){
        //necessario para colocar a imagem
        ImageIcon referencia = new ImageIcon("Imagens\\images.png");
        imagem = referencia.getImage();

        //define as coordenadas da nave
        this.x = 100;
        this.y = 100;
    }

    public void mexer(){
        x += dx;
        y += dy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImagem(){
        return imagem;
    }
    //metodos para realização da ação...capturando dados do teclado
    //evento do teclado
    public void keyPressed(KeyEvent tecla){
        //codigo de teclas é ASCII
        int codigo = tecla.getKeyCode();

        //se eu quero subir é necessario diminuir minhas coordenadas
        if (codigo == KeyEvent.VK_UP){
            //dy necessita ser negativo para a nave subir...quanto menor o número, mais rapido é o movimento da Nave
            dy = -1;
        }
        //se eu quero descer é necessario aumentar minhas coordenadas
        if (codigo == KeyEvent.VK_DOWN){
            dy = 1;
        }
        if (codigo == KeyEvent.VK_LEFT){
            dx = -1;
        }
        if (codigo == KeyEvent.VK_RIGHT){
            dx = 1;
        }

    }
    //para que quando apertar a tecla a nave não continue a se movimentar infinitamente
    //ou seja, assim que o jogador soltar a tecla a nave irá parar
    public void keyReleased(KeyEvent tecla){

        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_UP){
            dy = 0;
        }
        if (codigo == KeyEvent.VK_DOWN){
            dy = 0;
        }
        if (codigo == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }



}
