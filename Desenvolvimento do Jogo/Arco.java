import javax.swing.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Arco {
	private int x, y;
    private Image imagem_acao, imagem1, imagem2, imagem1_cima, imagem2_cima;
    private int altura, largura;
    private boolean isVisible;
    private int contador_forca = 0;

    private List<Flecha> flechas;

    private List<FlechaCima> flechas_cima;    

    public Arco(){

        ImageIcon arco_de_acao = new ImageIcon("images\\arco.png");
        imagem_acao = arco_de_acao.getImage();

        ImageIcon arco_solto = new ImageIcon("images\\arco.png");
        imagem1 = arco_solto.getImage();

        ImageIcon arco_solto_cima = new ImageIcon("images\\arco_cima.png");
        imagem1_cima = arco_solto_cima.getImage();

        ImageIcon arco_puxado = new ImageIcon("images\\arco_puxado.png");
        imagem2 = arco_puxado.getImage();

        ImageIcon arco_puxado_cima = new ImageIcon("images\\arco_puxado_cima.png");
        imagem2_cima = arco_puxado_cima.getImage();

        altura = imagem1.getHeight(null);
        largura = imagem1.getWidth(null);

        flechas = new ArrayList<Flecha>();

        flechas_cima = new ArrayList<FlechaCima>();

        this.x = 100;
        this.y = 200;
    }
    public List<Flecha> getFlechas(){
        return flechas;
    }
    public List<FlechaCima> getFlechasCima(){
        return flechas_cima;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Image getImagem1(){
        return imagem1;
    }
    public Image getImagem1_cima(){
        return imagem1_cima;
    }
    public Image getImagem2(){
        return imagem2;
    }
    public Image getImagem2_cima(){
        return imagem2_cima;
    }
    public Image getImagemAcao() {
        return imagem_acao;
    }

    public boolean isVisible(){
        return isVisible;
    }
    public void setVisible(boolean isVisible){
        this.isVisible = isVisible;
    }

    public void atira(){
        this.flechas.add(new Flecha(x + largura, y + altura/2));
    }
    public void atiraCima(){
        this.flechas_cima.add(new FlechaCima(x + largura, y + altura/2));
    }    

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP){           
           imagem_acao = imagem1_cima;
        }
        if(codigo == KeyEvent.VK_DOWN && imagem_acao == imagem1_cima){
            imagem_acao = imagem1;
        }

        if (imagem_acao == imagem1_cima) {
            if (codigo == KeyEvent.VK_SPACE) {
            	contador_forca += 1;
            	//System.out.println(contador_forca);
                imagem_acao = imagem2_cima;
            }
        }
        else {
            if (codigo == KeyEvent.VK_SPACE) {
            	contador_forca += 1;
            	//System.out.println(contador_forca);
                imagem_acao = imagem2;
            }
        }

    }
    public void keyReleased(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP){
            imagem_acao = imagem1_cima;
        }
        if(codigo == KeyEvent.VK_DOWN && imagem_acao == imagem1_cima){
            imagem_acao = imagem1;
        }

        if (imagem_acao == imagem2_cima) {
            if (codigo == KeyEvent.VK_SPACE) {
                atiraCima();
                imagem_acao = imagem1_cima;

            }
        }
        else {
            if (codigo == KeyEvent.VK_SPACE) {
            	contador_forca += 1;
                imagem_acao = imagem1;
                atira();
            }
        }
    }
    
    public int getContadorForca() {
    	return contador_forca;
    }
    
    public void setContadorForca(int contador_forca) {
    	this.contador_forca = contador_forca;
    }

}
