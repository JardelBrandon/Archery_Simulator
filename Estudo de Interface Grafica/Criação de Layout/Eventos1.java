import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//biblioteca de ações

//implements ActionListener => passa a ação para botão
public class Eventos1 extends JFrame{
    //Todos os atributos que se chamarem de "botão" serão passado para o objeto botão
    //JButton botao;

    public Eventos1(){
        //super("Eventos");
        super("Label");

        //caixa para texto
        JLabel simples = new JLabel("Label Teste");
        //mostra uma mensagem quando passa o mouse sobre o texto
        simples.setToolTipText("Meu Label");

        // o "|" faz acresentar mais aspectos a fonte
        //...................tipo de fonte, negrito | italico, tamanho da fonte
        Font fonte = new Font("serif", Font.BOLD | Font.ITALIC, 28);
        JLabel lcor = new JLabel("Label Teste 2");
        //necessario parametro para não ocorrer erro
        lcor.setFont(fonte);
        //define a cor da fonte
        lcor.setForeground(Color.BLUE);


        /*botao = new JButton("Clique Aqui");
        //adicional no painel o botão
        getContentPane().add(botao);
        //adiciona a ação ao botao...adicionando o objeto que chama o evento
        botao.addActionListener(this);*/

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(simples);
        //adiciona o label da fonte
        c.add(lcor);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
        c.add(simples);
    }
    //metodo para a ação do evento quando o botão é clicado
    //metodo necessário que se implementa o "ActionListener"
    /*public void actionPerformed(ActionEvent e){
        //evento que acontece...definida pelo usuário
        //System.out.println("Clicou");
        //define uma mensagem na janela
        botao.setText("Clicou");
    }*/

    public static void main(String[] args){
        new Eventos1();
    }
}
