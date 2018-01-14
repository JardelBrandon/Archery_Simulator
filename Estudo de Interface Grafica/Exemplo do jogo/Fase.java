package Programining3D;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Fase extends JPanel implements ActionListener{

    private Image fundo;
    private Nave nave;
    //atualiza a tela para cada movimento
    private Timer timer;
    //verifica se o jogo terminou ou não
    private boolean isEmJogo;

    private List<Inimigo> inimigos;

    //posições dos inimigos
    //matriz
    private int[][] coordenadas = {
            //as coordenadas são todas diferentes é para fazer
            // com que as não apareçam no mesmo local e nem no mesmo
            //tempo
            {2380, 29}, {2600, 59}, {1380, 89}, {780, 109},
            {580, 139}, {880, 239}, {790, 259}, {760, 50},
            {790, 150}, {1980, 209}, {560, 45},{510, 70},
            {930, 159}, {590, 80}, {530, 60}, {940, 59},
            {990, 30}, {920, 200}, {900, 259}, {660, 50},
            {540, 90}, {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}, {920, 300},
            {856, 328}, {456, 320}
    };


    public Fase(){

        setFocusable(true);
        //para que não haja Delay na atualização da tela
        setDoubleBuffered(true);
        //Listener que executa a ação instanciando a metódo TecladoAdapter
        addKeyListener(new TecladoAdapter());
        ImageIcon referencia = new ImageIcon("Imagens\\fundo.png");
        fundo = referencia.getImage();

        nave = new Nave();

        isEmJogo = true;

        inicializaInimigos();

        //define o tempo - em milisegundo - e a ação listener
        timer = new Timer(5,this);
        //metodo que inicia o timer
        timer.start();
    }

    public void inicializaInimigos(){
        inimigos = new ArrayList<Inimigo>();
        //.lenght = quantidade de elementos que existe na matriz
        for(int i = 0; i < coordenadas.length; i++){
            inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));
        }
    }
    //pinta a tela
    public void paint(Graphics g){
        //necessário para pintar imagens em 2D
        //casting para o Graphics2D
        Graphics2D graficos = (Graphics2D) g;
        //desenha o fundo da tela...observer = ação a ser executada
        // nesse caso o "null" é para que a imagem fique estático.
        graficos.drawImage(fundo, 0,0,null);

        if(isEmJogo) {
            //desenha a nave na tela
            //this = para que a nave se movimente, ele pega as informações da classe nave
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

            List<Missel> misseis = nave.getMisseis();
            //intera todos os elementos da lista e imprimir o misseis na tela
            //metodo ".size" ver o tamanho da lista
            //faz com que adicione um missil por vez na lista de misseis
            for (int i = 0; i < misseis.size(); i++) {
                //(Missel) = casting da classe missel
                Missel m = (Missel) misseis.get(i);
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            }
            //desenha os inimigos
            for (int i = 0; i < inimigos.size(); i++) {
                Inimigo in = inimigos.get(i);
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            }
            //colocar uma cor na frase
            graficos.setColor(Color.WHITE);
            //coloca uma frase na tela
            graficos.drawString("Inimigos" + inimigos.size(), 5, 15);

        }
        //game over
        else{
            ImageIcon fimJogo = new ImageIcon("Imagens\\game_over.jpg");
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }
        //limpa a tela para proxima pintura
        g.dispose();
    }

    //faz a ação de mexer a nave
    public void actionPerformed(ActionEvent arg0){

        if(inimigos.size() == 0){
            isEmJogo = false;
        }

        List<Missel> misseis = nave.getMisseis();

        //movimento do missel apartir da lista de misseis
        for(int i = 0; i < misseis.size(); i++){
            Missel m = (Missel)misseis.get(i);
            if(m.isVisible()){
                m.mexer();
            }
            else{
                misseis.remove(i);
            }
        }
        //movimento do inimigo apartir da matriz de inimigos
        for(int i = 0; i < inimigos.size(); i++){
            Inimigo in = inimigos.get(i);
            if(in.isVisible()){
                in.mexer();
            }
            else{
                inimigos.remove(i);
            }
        }
        nave.mexer();
        checarColisoes();
        //ação é uma repetição de imagens, onde a primeira imagem é esquecida
        // e a próxima é sobreposta
        repaint();
    }

    public void checarColisoes(){
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissel;
        //faz a verificação se a nave se colide com os inimigos
        for(int i = 0; i < inimigos.size(); i++){
            //objeto temporario que sempre vai receber um novo objeto
            Inimigo tempInimigo = inimigos.get(i);
            formaInimigo = tempInimigo.getBounds();
            //verifica se o retangulo da nave intersectou
            //...o retangulo do inimigo
            if(formaNave.intersects(formaInimigo)){
                nave.setVisible(false);
                tempInimigo.setVisible(false);
                isEmJogo = false;
            }
        }

        List<Missel> misseis = nave.getMisseis();
        //para cada missel é necessario verificar o inimigo
        for(int i = 0; i < misseis.size(); i++){
            //primeiro "for" pega o missel e a forma do retangulo
            Missel tempMissel = misseis.get(i);
            formaMissel = tempMissel.getBounds();

            //teste para cada elemento da lista de inimigos
            //testa se cada missel conseguiu intersepitar o retangulo do inimigo
            for(int j = 0; j < inimigos.size(); j++){
                //o inimigo não ficará visivel quando o missel o atingir
                Inimigo tempInimigo = inimigos.get(j);
                formaInimigo = tempInimigo.getBounds();
                //missel acerta o inimigo
                if(formaMissel.intersects(formaInimigo)){
                    tempInimigo.setVisible(false);
                    //o missel so consegue acertar um inimigo
                    tempMissel.setVisible(false);
                }
            }

        }
    }

    //pega os eventos do teclado
    private class TecladoAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e){

            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                //da o reestart do jogo
                isEmJogo = true;
                nave = new Nave();
                //reinicia os inimigos
                inicializaInimigos();
            }
            //como o objeto nave ja tem as ações keyPressed e keyReleased
            //não foi necessitado a utilização super.keyPressed
            nave.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            nave.keyReleased(e);
        }
    }
}