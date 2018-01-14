package Programining3D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teste extends JFrame {
    public Teste(){

        //criação de menus
        JMenuBar barraMenu = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        JMenuItem sobre = new JMenuItem("Sobre");
        sobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mostra uma mini janela
                JOptionPane.showMessageDialog(null, "Jogo desenvolvido", "Informações", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sai do sistema sem necessitar de clicar no "X"
                System.exit(0);
            }
        });

        menu.add(sobre);
        //linha que separa botões
        menu.add(new JSeparator());
        menu.add(sair);

        barraMenu.add(menu);

        //seta a barra de menu na aplicação
        setJMenuBar(barraMenu);
        add(new Fase());
        setTitle("Meu Primeiro jogo");
        //a barra de menu são 20 pixels...por se aumenta 20 no eixo y
        setSize(500,420);
        //redimenciona a janela...false = não pode redimencionar
        setResizable(false);
        setVisible(true);
        //mostra onde a janela vai aparecer...null = centro da tela
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        new Teste();
    }
}