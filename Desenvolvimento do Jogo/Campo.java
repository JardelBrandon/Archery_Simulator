import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Campo extends JPanel implements ActionListener {
	
	private Image campo;
    private Arco arco;
    private Timer timer;
	
	public Campo() {
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

        graficos.drawImage(arco.getImagemAcao(), arco.getX(), arco.getY(), this);

        List<Flecha> flechas = arco.getFlechas();
        for(int i = 0; i < flechas.size(); i++){
            Flecha f = (Flecha) flechas.get(i);
            graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this);
        }

        List<FlechaCima> flechaCimas = arco.getFlechasCima();
        for(int i = 0; i < flechaCimas.size(); i++){
            FlechaCima fc = (FlechaCima) flechaCimas.get(i);
            graficos.drawImage(fc.getImagem(), fc.getX(), fc.getY(), this);
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent arg0){

        List<FlechaCima> flechaCimas = arco.getFlechasCima();

        for(int i = 0; i < flechaCimas.size(); i++){
            FlechaCima fc = (FlechaCima) flechaCimas.get(i);
            if(fc.isVisibleCima()){
                fc.mexerCima();
            }
            else{
                flechaCimas.remove(i);
            }
        }

        List<Flecha> flechas = arco.getFlechas();

        for(int i = 0; i < flechas.size(); i++){
            Flecha f = (Flecha) flechas.get(i);
            if(f.isVisible()){
                f.mexer();
                /*if(f.getX() == 500) {
                	f.mexer_baixo();
                }*/
            }
            else{
                flechas.remove(i);
            }
        }
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