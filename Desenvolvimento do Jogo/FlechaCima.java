import javax.swing.*;
import java.awt.*;
public class FlechaCima {
    private Image imagem_2;
    private int x, y;
    private int altura_cima, largura_cima;
    private boolean isVisibleCima;
    private static final int LARGURA_TELA = 1425;
    private static final int ALTURA_TELA = 629;
    //obs.: mudar esta constante quando for implementar a força do arco
    private static final int VELOCIDADE = 2;

    public FlechaCima(int x, int y){
        this.x = x;
        this.y = y;

        ImageIcon flecha_cima = new ImageIcon("images\\flecha_cima.png");
        imagem_2 = flecha_cima.getImage();

        this.altura_cima = imagem_2.getHeight(null);
        this.largura_cima = imagem_2.getWidth(null);

        isVisibleCima = true;

    }
    public Image getImagem_2(){
        return imagem_2;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isVisibleCima(){
        return isVisibleCima;
    }
    public void setVisibleCima(boolean isVisibleCima){
        this.isVisibleCima = isVisibleCima;
    }
    public void mexerCima(){
        this.x += VELOCIDADE;
        this.y -= VELOCIDADE;

        if(this.x > LARGURA_TELA){
            isVisibleCima = false;
        }
        if(this.y > ALTURA_TELA){
            isVisibleCima = false;
        }
    }
}