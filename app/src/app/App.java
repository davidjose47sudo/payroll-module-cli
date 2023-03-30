/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import java.util.Scanner;

/**
 *
 * @author ADSI
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String options = "saludo";
        Scanner leer = new Scanner(System.in);
        System.out.println("ingrese una  accion");
        options = leer.nextLine();
        switch (options) {
            case "saludo":
                System.out.println("Hola mundo david");
                // secuencia de sentencias.
                break;
            case "despedida":
                System.out.println("chao martin");
                // secuencia de sentencias.
                break;
            case "hablar":
                System.out.println("como estas diego?");
                // secuencia de sentencias.
                break;
            default:
                System.out.println("Hola mundo");

        }

    
       
    }

}
