/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jarde
 */
public class NewClass1 {
    public static void main(String[] args) {
        while (NewClass.valor < 500){
        System.out.println(NewClass.valor);
        NewClass.incrementa();
        }
        NewClass.valor = 69;
        System.out.println(NewClass.valor);
        }
        
}
