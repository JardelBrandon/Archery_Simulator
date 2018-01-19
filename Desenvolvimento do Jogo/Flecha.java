import javax.swing.*;
import java.awt.*;

public class Flecha {
	
	private Image imagem;
    private int x, y;
    private int altura, largura;
    private boolean isVisible;
    private static final int LARGURA_TELA = 1425;
    //obs.: mudar esta constante quando for implementar a força do arco
    //private int contador_forca;
    
    private Arco arco;
    
    private static final int VELOCIDADE = 2;   

    public Flecha(int x, int y){

        this.x = x;
        this.y = y;

        ImageIcon flecha = new ImageIcon("images\\flecha.png");
        imagem = flecha.getImage();

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);

        isVisible = true;

    }    
    
    public void mexer(){
    	
    	//apartir da força definir a velocidade do tiro
    	/*arco = new Arco();
    	velocidade = arco.getContadorForca();*/
    	
        this.x += VELOCIDADE;
        System.out.println(VELOCIDADE);
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
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}