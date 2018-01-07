import javax.swing.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Arco {

    private int x, y;
    private Image imagem1, imagem2;

    public Arco(){
        ImageIcon arco_solto = new ImageIcon("images\\arco.png");
        imagem1 = arco_solto.getImage();

        ImageIcon arco_puxado = new ImageIcon("images\\arco_puxado.png");
        imagem2 = arco_puxado.getImage();

        this.x = 100;
        this.y = 200;
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


    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_LEFT){
            imagem1 = imagem2;
        }
    }

    public void keyReleased(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_RIGHT){
            imagem2 = imagem1;
        }
    }
}

