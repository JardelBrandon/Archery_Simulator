/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

/**
 *
 * @author robotica
 */
public class Protocolo {
    private String tipoDado;
    private String giroscopio;
    private String rotaryEncoder;
    
    private String leituraComando;
    
    private void interpretaComando() {
        //Separa a string de comando em substrings delimitadas por um caractere especifico
        // &COISA, 0, 0 
        String aux[] = leituraComando.split(",");
        if (aux.length == 3){
            tipoDado = aux[0];
            giroscopio = aux[1];
            rotaryEncoder = aux[2];
        }
    }

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }

    public String getGiroscopio() {
        return giroscopio;
    }

    public void setGiroscopio(String giroscopio) {
        this.giroscopio = giroscopio;
    }

    public String getRotaryEncoder() {
        return rotaryEncoder;
    }

    public void setRotaryEncoder(String rotaryEncoder) {
        this.rotaryEncoder = rotaryEncoder;
    }

    public String getLeituraComando() {
        return leituraComando;
    }

    public void setLeituraComando(String leituraComando) {
        this.leituraComando = leituraComando; //Temos a string de dados
        this.interpretaComando(); //Interpretamos a string
    }
}
