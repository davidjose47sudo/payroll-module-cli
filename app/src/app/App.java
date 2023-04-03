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
    static String name, lastname, type_job;
    static int clockswork = 0, clockworkex = 0, job = 0;
    static double salud = 0, pension = 0, salarybruto = 0, arl = 0, salaryneto = 0, htex = 0, sp = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int options;
        boolean menu = true;

        while (menu) {
            clr();
            System.out.println("Menu");
            System.out.println("ACCIONES:");
            System.out.println("1)agregar nomina de empleado administrativo");
            System.out.println("2)agregar nomina de empleado operativo");
            System.out.println("3)Imprimir Nomina");
            System.out.println("4)Salir");
            System.out.println("Numero de accion:");
            options = leer.nextInt();
            switch (options) {
                case 1:
                    information(options);
                    break;
                case 2:
                    information(options);
                    break;
                case 3:
                    System.out.println("Imrimiendo registros");
                    break;
                case 4:
                    menu = false;
                    break;
                default:
                    System.out.println("opcion no valida");
                    System.out.print("Presiona Enter para continuar...");
                    leer.nextLine(); // Espera a que el usuario presione Enter
                    leer.nextLine(); // Espera a que el usuario presione Enter
            }

        }

    }

    static void imprimir(String name, String lastname, int clocks, double salary, int clocks_ex, Double allpayEx,
            double salud, double pension, double arl, Double Descuentos, Double pay) {
        clr();
        System.out.println("*************************************************");
        System.out.println("**************** VOLANTE DE PAGO ****************");
        System.out.println("*************************************************");
        System.out.println("Nombre:" + name + " " + lastname);
        System.out.println("Cargo:" + type_job);
        System.out.println("Horas Trabajadas (mes):" + clocks);
        System.out.println("Salario Bruto:$" + salary);
        System.out.println("Horas Extras:" + clocks_ex);
        System.out.println("Total pago Horas Extras: $" + allpayEx);
        System.out.println("*************************************************");
        System.out.println("*************** DESCUENTOS DE LEY ***************");
        System.out.println("*************************************************");
        System.out.println("Salud(4%): $" + salud);
        System.out.println("Pensión: $" + pension);
        System.out.println("ARL(0,522%): $" + arl);
        System.out.println("Total Descuentos: $" + Descuentos);
        System.out.println("Total a pagar: $" + pay);
        System.out.println("*************************************************");
        System.out.println("************ FIN DEL VOLANTE DE PAGO ************");
        System.out.println("*************************************************");
        System.out.print("Presiona Enter para continuar...");
    leer.nextLine(); // Espera a que el usuario presione Enter
    leer.nextLine(); // Espera a que el usuario presione Enter
    }

    static void information(int options) {
        System.out.println("nombre del trabajador:");
        leer.nextLine();
        name = leer.nextLine();
        System.out.println("apellido del trabajador:");
        lastname = leer.nextLine();    
      clr();
        if (options == 1) {
            do {
                System.out.println("titulo de trabajo(ingrese el numero):");
                System.out.println("1)Auxiliar Administrativo");
                job = leer.nextInt();
                System.out.println("horas trabajadas (mes)");
                clockswork = leer.nextInt();
                System.out.println("horas extras trabajadas (mes)");
                clockworkex = leer.nextInt();
            } while (job == 0);
        }
        if (options == 2) {
            do {
                System.out.println("titulo de trabajo(ingrese el numero):");
                System.out.println("1)Conductor");
                System.out.println("2)Oficios Generales");
                System.out.println("3)Vigilancia");
                job = leer.nextInt();
                System.out.println("horas trabajadas (mes)");
                clockswork = leer.nextInt();
            } while (job == 0);
        }
         
        htex = clock_expay(options, clockworkex);
        salarybruto = Salary(options, clockswork);
        if (clockworkex == 0) {
            salud = hearthand(options, salarybruto);
            pension = pension(options, salarybruto);
            arl = ARL(options, job, salarybruto);
        }
        if (clockworkex > 0) {
            salud = hearthand(options, salarybruto + htex);
            pension = pension(options, salarybruto + htex);
            arl = ARL(options, job, salarybruto + htex);
        }
       
         
       
        salaryneto = salarybruto - salud - pension + htex;
        sp = salud + pension;
        imprimir(name, lastname, clockswork, salarybruto, clockworkex, htex, salud, pension, arl, sp,
                salaryneto);
        cleanVariable();
        clr();
    }

    static int Salary(int typejob, int clockwork) {
        return clocks_pay(typejob, clockwork);
    }

    static double ARL(int type_job, int job, double salary) {
        if (type_job == 1) {
            App.type_job = "Auxiliar Administrativo";
            return 0.00522 * salary;
        } else {
            if (job == 1) {
                App.type_job = "Oficios Generales";
                return 0.00522 * salary;
            }
            if (job == 2) {
                App.type_job = "Conductor";
                return 0.01044 * salary;
            }
            if (job == 3) {
                App.type_job = "Vigilancia";
                return 0.0435 * salary;
            }
            return 0;
        }
    }

    static int clocks_pay(int type_job, int clocks) {
        if (type_job == 1) {
            return clocks * 20000;
        } else {
            return clocks * 40000;
        }
    }
    static void clr() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    static int clock_expay(int type_job, int clocks) {
        if (type_job == 1) {
            return clocks * 25000;
        }
        return 0;
    }

    

    static double hearthand(int type_job, double salary) {
        if (type_job == 1) {
            return 0.04 * salary;
        } else {
            return 0.125 * salary;
        }
    }

    static void cleanVariable() {
        App.name = "";
        App.lastname = "";
        App.type_job = " ";
        App.clockswork = 0;
        App.clockworkex = 0;
        App.job = 0;
        App.salud = 0;
        App.pension = 0;
        App.salarybruto = 0;
        App.arl = 0;
        App.salaryneto = 0;
        App.htex = 0;
        App.sp = 0;
    }

    static double pension(int type_job, double salary) {
        if (type_job == 1) {
            return 0.04 * salary;
        } else {
            return 0.125 * salary;
        }
    }
}
