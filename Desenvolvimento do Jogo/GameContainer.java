import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameContainer extends JFrame{
	
	public GameContainer(){
        add(new Campo());
        setSize(1571,1000);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {		
		new GameContainer();
	}

}
