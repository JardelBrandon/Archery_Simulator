package Programining3D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Nave {
    //define as coordenadas da nave
    private int x, y;
    //define as coordenadas do movimento da nave
    private int dx, dy;
    //posição dos misseis
    private int altura, largura;
    //para saber se a nave foi acertada ou não
    private boolean isVisible;
    private Image imagem;
    //nave com capacidade de atirar com o numero de misseis indeterminado
    //lista dinâmica que sempre está adicionando elementos
    //<Missel> = estrutura de misseis
    private List<Missel> misseis;

    public Nave(){
        //necessario para colocar a imagem
        ImageIcon referencia = new ImageIcon("Imagens\\nave.gif");
        imagem = referencia.getImage();

        //metodos ".getHeight e .getWidth" são para retornar a posição da imagem na tela
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
        //adiciona misseis a nave
        misseis = new ArrayList<Missel>();

        //define as coordenadas da nave
        this.x = 100;
        this.y = 100;
    }

    public void mexer(){
        //ver as coordenadas para que não passe a tela
        //System.out.println(x + "," + y);
        x += dx;// 1 e 462
        y += dy;//1 e 340
        //condições para que a nave não passe da tela
        if(this.x < 1){
            //nave para como existisse uma parede
            x = 1;
        }
        if(this.x > 462){
            x = 462;
        }
        if(this.y < 1){
            y = 1;
        }
        if(this.y > 340){
            y = 340;
        }
    }
    public List<Missel> getMisseis(){
        return misseis;
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
    public boolean isVisible(){
        return isVisible;
    }
    public void setVisible(boolean isVisible){
        this.isVisible = isVisible;
    }
    //adiciona misseis a lista de misseis
    public void atira(){
        //new Missel() = posição onde o missel irá aparecer
        //y + altura/2 = faz com que o missel apareça no centro da nave
        this.misseis.add(new Missel(x + largura, y + altura / 2));
    }
    //retorna um retangulo..getBounds = forma do retangulo
    public Rectangle getBounds(){
        //objeto que trata a colisão
        return new Rectangle(x, y, largura, altura);
    }
    //metodos para realização da ação...capturando dados do teclado
    //evento do teclado
    public void keyPressed(KeyEvent tecla){
        //codigo de teclas é ASCII
        int codigo = tecla.getKeyCode();

        //atira na barra de espaço
        if(codigo == KeyEvent.VK_SPACE){
            atira();
        }
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