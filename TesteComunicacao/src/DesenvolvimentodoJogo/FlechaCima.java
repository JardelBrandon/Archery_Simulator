package DesenvolvimentodoJogo;


import javax.swing.*;
import java.awt.*;

//classe criada para conseguir fazer uma lista com os tiros de flecha para cima
public class FlechaCima {
	
    private Image imagem_cima, imagem_acao, imagem_baixo, imagem_meio;
    private double x, y;    
    private int altura_cima, largura_cima;
    private boolean isVisibleCima;
    private static final int LARGURA_TELA = 1425;
    private static final int ALTURA_TELA = 629;
    //obs.: mudar esta constante quando for implementar a for�a do arco
    private double contador_forca;
    private double contador_cima;
    private static final int ALTURA_FIGURA = 196;    
    

    public FlechaCima(double x, double y, double forca, double cima){
    	//necessario para pegar as informacoes da posicao da imagem e do contador quando se aperta barra de espaco
        this.x = x;
        this.y = y;       
        
        this.contador_forca = forca;
        this.contador_cima = cima;

        ImageIcon flecha_acao = new ImageIcon("C:\\Users\\jarde\\OneDrive\\Documentos\\GitHub\\Archery_Simulator\\TesteComunicacao\\src\\DesenvolvimentodoJogo\\images\\flecha_cima.png");
        imagem_acao = flecha_acao.getImage();
        
        ImageIcon flecha_baixo = new ImageIcon("C:\\Users\\jarde\\OneDrive\\Documentos\\GitHub\\Archery_Simulator\\TesteComunicacao\\src\\DesenvolvimentodoJogo\\images\\flecha_baixo.png");
        imagem_baixo = flecha_baixo.getImage();
        
        ImageIcon flecha_meio = new ImageIcon("C:\\Users\\jarde\\OneDrive\\Documentos\\GitHub\\Archery_Simulator\\TesteComunicacao\\src\\DesenvolvimentodoJogo\\images\\flecha.png");
        imagem_meio = flecha_meio.getImage();
        
        //define a posicao do inicio do tiro da flecha apartir da posicao do arco
        this.altura_cima = imagem_acao.getHeight(null);
        this.largura_cima = imagem_acao.getWidth(null);

        isVisibleCima = true;        

    }    
    
    public Image getImagem(){
        return imagem_acao;
    }
    public double getX(){
        return x;
    }
    public double getY(){
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
    	/*if(contador_forca > 10) {
    		contador_forca = 10;
    	}
    	if(contador_cima > 10) {
    		contador_cima = 10;
    	}*/    	
    	
    	if(this.x == LARGURA_TELA/(contador_cima/10)) {
    		imagem_acao = imagem_meio;
    	}    	
    	else if(this.x > LARGURA_TELA/(contador_cima/10)){
    		this.y += contador_cima;
    		imagem_acao = imagem_baixo;
    	}
    	else {
    		this.y -= contador_cima;    		
    	}    	
    	
        this.x += contador_forca;        
               
        if(this.x > LARGURA_TELA){
            isVisibleCima = false;
        }        
        //obs.:retirar essa condicao quando definir a distancia em que a flecha 
        //ira perder a forca
        if(this.y > ALTURA_TELA){
            isVisibleCima = true;
        }
    }

}