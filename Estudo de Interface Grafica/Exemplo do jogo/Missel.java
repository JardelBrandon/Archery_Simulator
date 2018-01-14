package Programining3D;

import javax.swing.*;
import java.awt.*;

public class Missel {

    private Image imagem;
    private int x, y;
    private int altura, largura;
    //para verificar se o missil está visivel ou não
    // ...sempre começar um atributo booleano com "is".
    private boolean isVisible;
    //para que o missil não ultrapasse a tela
    // ...static final = uma constante.
    private static final int LARGURA_TELA = 500;
    private static final int VELOCIDADE = 2;

    //coordenadas da nave = x e y
    public Missel(int x, int y){

        this.x = x;
        this.y = y;

        ImageIcon referencia = new ImageIcon("Imagens\\missel.png");
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisible = true;
    }
    public void mexer(){
        //disparo definido na horizontal na velocidade da constante
        this.x += VELOCIDADE;
        if(this.x > LARGURA_TELA){
            //quando o missil passa dos limites da tela, ele some.
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
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    //retorna um retangulo..getBounds = forma do retangulo
    public Rectangle getBounds(){
        //objeto que trata a colisão
        return new Rectangle(x, y, largura, altura);
    }
}