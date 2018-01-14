package Programining3D;

import javax.swing.*;
import java.awt.*;

public class Inimigo {
    private Image imagem;
    private int x, y;
    private int altura, largura;
    //para verificar se o missil está visivel ou não
    // ...sempre começar um atributo booleano com "is".
    private boolean isVisible;
    //para que o missil não ultrapasse a tela
    // ...static final = uma constante.
    private static final int LARGURA_TELA = 500;
    private static final int VELOCIDADE = 1;
    //varialvel de cada inimigo...necessita ser de classe
    // ...colocando o static
    private static int contador = 0;

    //coordenadas da nave = x e y
    public Inimigo(int x, int y){

        this.x = x;
        this.y = y;

        ImageIcon referencia;
        if(contador++ % 3 == 0){
            referencia = new ImageIcon("Imagens\\inimigo_1.gif");
        }
        else{
            referencia = new ImageIcon("Imagens\\inimigo_2.gif");
        }
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisible = true;
    }
    public void mexer(){
        //faz com que entre em um lupe infinito
        //x < 0 = chegou ao final da tela
        if(this.x < 0){
            this.x = LARGURA_TELA;
        }
        //o inimigo se movimenta do final da tela para o inicial
        else{
            this.x -= VELOCIDADE;
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
    //facilita na hora de saber a colisão...pois quando os dois retangulos
    //se chocarem o programa saberá da colisão
    public Rectangle getBounds(){
        //objeto que trata a colisão
        return new Rectangle(x, y, largura, altura);
    }
}