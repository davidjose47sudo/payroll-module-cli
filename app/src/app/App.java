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
    static Scanner leer = new Scanner(System.in);
    static String job, name, lastname;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int options;
        boolean menu = true;

        while (menu) {
            System.out.println("Menu");
            System.out.println("ACCIONES:");
            System.out.println("1)administrativo");
            System.out.println("2)operativo");
            System.out.println("3)Salir");
            System.out.println("tipo de empleado:");
            options = leer.nextInt();
            switch (options) {
                case 1:
                    information();
                    break;
                case 2:
                    System.out.println("empleado de tipo operativo");
                    break;
                case 3:
                    menu = false;
                    break;
                default:
                    System.out.println("no existe ese cargo");
            }

        }

    }

    static void imprimir(String name, String lastname) {
        System.out.println("*************************************************");
        System.out.println("**************** VOLANTE DE PAGO ****************");
        System.out.println("*************************************************");
        System.out.println("Nombre: Ana Maria Jaimes González");
        System.out.println("Cargo: Auxiliar Administrativo");
        System.out.println("Horas Trabajadas (mes): 40");
        System.out.println("Salario Bruto: $800, 000");
        System.out.println("Horas Extras: 20");
        System.out.println("Total pago Horas Extras: 3500, 000");
        System.out.println("*************************************************");
        System.out.println("*************** DESCUENTOS DE LEY ***************");
        System.out.println("*************************************************");
        System.out.println("Salud(4%): $52000");
        System.out.println("Pensión: $52000");
        System.out.println("ARL(0,522%): $6786");
        System.out.println("Total Descuentos: $104000");
        System.out.println("Total a pagar: $1,189,214");
        System.out.println("*************************************************");
        System.out.println("************ FIN DEL VOLANTE DE PAGO ************");
        System.out.println("*************************************************");

    }

    static void information() {
        System.out.println("nombre del trabajador" + '\n');
        name = leer.nextLine();
        System.out.println("apellido del trabajador" + '\n');
        lastname = leer.nextLine();
        System.out.println("titulo de trabajo" + '\n');
        job = leer.nextLine();
        imprimir("name", "lastname");
    }

    static int Salary() {
        return 100;
    }

    static int ARL(String level) {
        return 100;
    }

    static int hearthand(String level) {
        return 100;
    }
}
