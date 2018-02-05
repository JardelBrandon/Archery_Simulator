import javax.swing.*;
import java.awt.*;

//classe criada para conseguir fazer uma lista com os tiros de flecha para cima
public class FlechaCima {
	
	private Image imagem;
    private int x, y;
    private int altura_cima, largura_cima;
    private boolean isVisibleCima;
    private static final int LARGURA_TELA = 1571;
    private static final int ALTURA_TELA = 1000;
    //obs.: mudar esta constante quando for implementar a força do arco
    private int contador_forca;
    private int contador_cima;
    private static final int ALTURA_FIGURA = 196;
    

    public FlechaCima(int x, int y, int forca, int cima){
    	//necessario para pegar as informacoes da posicao da imagem e do contador quando se aperta barra de espaco
        this.x = x;
        this.y = y;
        this.contador_forca = forca;
        this.contador_cima = cima;

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
    	if(contador_cima > 10) {
    		contador_cima = 10;
    	}
        this.x += contador_forca;        
        
        if(this.y < contador_cima + ALTURA_FIGURA) {        	
        	this.y += contador_cima;
        }
        
        this.y -= contador_cima;
               
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
