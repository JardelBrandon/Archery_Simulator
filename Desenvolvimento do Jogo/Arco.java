import javax.swing.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Arco {

    private int x, y;
    private Image imagem_acao,imagem1, imagem2;
    private int altura, largura;
    private boolean isVisible;

    private List<Flecha> flechas;

    public Arco(){

        ImageIcon arco_de_acao = new ImageIcon("images\\arco.png");
        imagem_acao = arco_de_acao.getImage();

        ImageIcon arco_solto = new ImageIcon("images\\arco.png");
        imagem1 = arco_solto.getImage();

        ImageIcon arco_puxado = new ImageIcon("images\\arco_puxado.png");
        imagem2 = arco_puxado.getImage();

        altura = imagem1.getHeight(null);
        largura = imagem1.getWidth(null);

        flechas = new ArrayList<Flecha>();

        this.x = 100;
        this.y = 200;
    }
    public List<Flecha> getFlechas(){
        return flechas;
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
    public Image getImagem2(){
        return imagem2;
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

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE){
            imagem_acao = imagem2;
        }
    }

    public void keyReleased(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_SPACE){
            imagem_acao = imagem1;
            atira();
        }
    }
}

