import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Senha extends JFrame implements ActionListener{
    //instaciando os nomes
    JButton logar, cancelar;
    JTextField login;
    JPasswordField senha;

    public Senha(){
        super("Senha");

        logar = new JButton("Logar:");
        logar.addActionListener(this);

        cancelar = new JButton("Cancelar:");
        cancelar.addActionListener(this);

        //referencia o objeto
        login = new JTextField();
        senha = new JPasswordField();

        Container c = getContentPane();
        c.setLayout(new GridLayout(3,2));
        c.add(new JLabel("Login:"));
        //componente para caixa de texto
        c.add(login);
        c.add(new JLabel("Senha:"));
        //componente para caixa de senha
        c.add(senha);
        //mesma coisa de fazer c.add(new JButton("Logar:")
        c.add(logar);
        c.add(cancelar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,150);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //necessita de um metodo para saber em qual botão foi clicado
        //e.getSource => procura o botão
        if (e.getSource() == logar){
            //exibir o valor capturado na caixa de texto
            //para não aparecer as "***" na senha instacia a classe String
            String s = "Login:" + login.getText() + "\nsenha:" + new String(senha.getPassword());
            //mostra painel com caixa de mensagem que nesse caso seria a variável "s"
            JOptionPane.showMessageDialog(null, s);
        }
        else if(e.getSource() == cancelar){
            //passa as informações de texto para objeto que nesse caso limpa = vazio
            login.setText("");
            senha.setText("");
        }
    }


    public static void main(String[] args){
        new Senha();
    }


}
