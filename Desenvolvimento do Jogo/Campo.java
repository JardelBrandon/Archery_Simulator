
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class Campo extends JPanel implements ActionListener{
    private Image campo;
    private Arco arco;
    private Timer timer;

    public Campo(){
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());

        ImageIcon image_fundo = new ImageIcon("images\\camp.jpg");
        campo = image_fundo.getImage();

        arco = new Arco();

        timer = new Timer(5, this);
        timer.start();

    }
    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(campo, 0, 0, null);

        graficos.drawImage(arco.getImagem1(), arco.getX(), arco.getY(), this);

        g.dispose();
    }

    public void actionPerformed(ActionEvent arg0){
        repaint();
    }
    private class TecladoAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            arco.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            arco.keyReleased(e);
        }
    }
}
