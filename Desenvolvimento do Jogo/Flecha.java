
import javax.swing.*;
import java.awt.*;


public class Flecha {

    private Image imagem_1;
    private int x, y;
    private int altura, largura;
    private boolean isVisible;
    private static final int LARGURA_TELA = 1425;
    //obs.: mudar esta constante quando for implementar a força do arco
    private static final int VELOCIDADE = 2;

    public Flecha(int x, int y){

        this.x = x;
        this.y = y;

        ImageIcon flecha = new ImageIcon("images\\flecha.png");
        imagem_1 = flecha.getImage();

        this.altura = imagem_1.getHeight(null);
        this.largura = imagem_1.getWidth(null);

        isVisible = true;

    }
    public void mexer(){
        this.x += VELOCIDADE;
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
        return imagem_1;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}