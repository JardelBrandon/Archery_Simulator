package Programining3D;

import javax.swing.*;

public class Teste extends JFrame {
    public Teste(){

        add(new Fase());
        setTitle("Meu Primeiro jogo");
        setSize(500,400);
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
