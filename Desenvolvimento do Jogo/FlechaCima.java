import javax.swing.*;
import java.awt.*;

//classe criada para conseguir fazer uma lista com os tiros de flecha para cima
public class FlechaCima {
	
	private Image imagem;
    private int x, y;
    private int altura_cima, largura_cima;
    private boolean isVisibleCima;
    private static final int LARGURA_TELA = 1425;
    private static final int ALTURA_TELA = 629;
    //obs.: mudar esta constante quando for implementar a força do arco
    private int contador_forca;
    

    public FlechaCima(int x, int y, int forca){
    	//necessario para pegar as informacoes da posicao da imagem e do contador quando se aperta barra de espaco
        this.x = x;
        this.y = y;
        this.contador_forca = forca;

        ImageIcon flecha_cima = new ImageIcon("images\\flecha_cima.png");
        imagem = flecha_cima.getImage();
        
        //define a posicao do inicio do tiro da flecha apartir da posicao do arco
        this.altura_cima = imagem.getHeight(null);
        this.largura_cima = imagem.getWidth(null);

        isVisibleCima = true;        

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
    public boolean isVisibleCima(){
        return isVisibleCima;
    }
    public void setVisibleCima(boolean isVisibleCima){
        this.isVisibleCima = isVisibleCima;
    }
    //metodo necessario para fazer com a flecha suba
    public void mexerCima(){
    	if(contador_forca > 10) {
    		contador_forca = 10;
    	}
        this.x += contador_forca;
        this.y -= contador_forca;
        System.out.println(contador_forca);
        if(this.x > LARGURA_TELA){
            isVisibleCima = false;
        }        
        //obs.:retirar essa condicao quando definir a distancia em que a flecha 
        //ira perder a forca
        if(this.y > ALTURA_TELA){
            isVisibleCima = false;
        }
    }

}
