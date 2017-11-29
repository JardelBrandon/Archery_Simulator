//bibliotecas
import javax.swing.*;
import java.awt.*;//criação dos layouts

//quando se extende a classe da biblioteca, não é necessário ficar rechamando a referência( que nesse caso seria frame)
public class Janela extends JFrame {
    //construtor
    public Janela(){
        //colocar um titulo a janela
        super("Primeira Janela");

        //toda a área da janela
        //container principal
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        //container secudário
        Container c2 = new JPanel();
        c2.setLayout(new GridLayout(4,1));
        c2.add(new JButton("OK"));
        c2.add(new JButton("Cancelar"));
        c2.add(new JButton("Delete"));
        c2.add(new JButton("Ajuda"));

        c.add(BorderLayout.CENTER,new JButton("Centro"));
        //faz com que o que estava escrito no segundo container vá para o local indicado(nesse caso o lado leste da janela)
        c.add(BorderLayout.EAST, c2);
        //quando fecha a janela, finaliza o processo do codigo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //define o tamanho da janela pela largura e altura
        setSize(300, 300);
        //faz com a janela fique vizivel(true) ou nao(false)
        setVisible(true);
    }
    public static void main(String[] args){
        new Janela();
    }
}
