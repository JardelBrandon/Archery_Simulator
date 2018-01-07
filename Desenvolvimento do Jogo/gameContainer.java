import javax.swing.JFrame;
import javax.swing.JPanel;

public class gameContainer extends JFrame{

    public gameContainer(){
        add(new Campo());
        setSize(1425,629);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new gameContainer();
    }
}
