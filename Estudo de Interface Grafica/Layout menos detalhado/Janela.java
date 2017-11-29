//bibliotecas
import javax.swing.JFrame;//criação da janela
import javax.swing.JButton;//criação de botoes
import java.awt.*;//criação dos layouts

//quando se extende a classe da biblioteca, não é necessário ficar rechamando a referência( que nesse caso seria frame)
public class Janela extends JFrame {
    //construtor
    public Janela(){
        //colocar um titulo a janela
        super("Primeira Janela");
        //instaciando as bibliotecas, necessario quando não extender classe à biblioteca.
        //JFrame frame = new JFrame();

        //toda a área da janela
        Container c = getContentPane();
        //BorderLayout boder = new BorderLayout();
        //O layout está dentro do container passando os espaçamento entre os botões
        //hgap => espaçamento horizontal; vgap => espaçamento vertical
        /*c.setLayout(new BorderLayout(50, 50));

        //JButton botao = new JButton();
        //adiciona os elementos do botão para janela
        //getContentPane().add(botao);

        //define a posição do botões que nesse caso são 1, 2 , 3, 4 e 5
        //layouts java para mais opções
        //O BorderLayout é necessária a definição da posição do botão
        c.add(BorderLayout.NORTH, new JButton("1"));
        c.add(BorderLayout.SOUTH, new JButton("2"));
        c.add(BorderLayout.CENTER, new JButton("3"));
        c.add(BorderLayout.EAST, new JButton("4"));
        c.add(BorderLayout.WEST, new JButton("5"));*/

        //ajusta a posição dos botões automaticamente
        /*c.setLayout(new FlowLayout());
        //outro tipo de layout
        c.add(new JButton("1"));
        c.add(new JButton("2"));
        c.add(new JButton("3"));*/

        //outro tipo de layout...não gostei.
        //rows => linha; cols => colunas
        /*c.setLayout(new GridLayout(3, 3));
        c.add(new JButton("1"));
        c.add(new JButton("2"));
        c.add(new JButton("3"));
        c.add(new JButton("4"));*/

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
