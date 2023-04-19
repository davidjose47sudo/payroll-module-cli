/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author ADSI
 */
public class App {

    static NumberFormat formatea = NumberFormat.getCurrencyInstance((new Locale("es", "CO")));
    static Scanner leer = new Scanner(System.in);
    static String name, lastname, type_job, Work;
    static int clockswork = 0, clockworkex = 0, job = 0;
    static double salud = 0, pension = 0, salarybruto = 0, arl = 0, salaryneto = 0, htex = 0, sp = 0;
    static protected int options;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            App.options = leer.nextInt();
            switch (options) {
                case 1:
                    information(options);
                    break;
                case 2:
                    information(options);
                    break;
                case 3:
                    registorprint();
                    break;
                case 4:
                    menu = false;
                    break;
                default:
                    System.out.println("opcion no valida");
                    pause();
            }
        }
    }

    static void imprimir(String name, String lastname, int clocks, String salary, int clocks_ex, String allpayEx,
            String salud, String pension, String arl, String Descuentos, String pay) {
        clr();
        System.out.println("*************************************************");
        System.out.println("**************** VOLANTE DE PAGO ****************");
        System.out.println("*************************************************");
        System.out.println("Nombre:" + name + " " + lastname);
        System.out.println("Cargo:" + type_job);
        System.out.println("Horas Trabajadas (mes):" + clocks);
        System.out.println("Salario Bruto:" + salary);
        if (App.options == 1) {
            System.out.println("Horas Extras:" + clocks_ex);
            System.out.println("Total pago Horas Extras: " + allpayEx);
        }
        System.out.println("*************************************************");
        System.out.println("*************** DESCUENTOS DE LEY ***************");
        System.out.println("*************************************************");
        if (App.options == 1) {
            System.out.println("Salud(4%): " + salud);
            System.out.println("Pensión(4%): " + pension);
            System.out.println("ARL(0,522%): " + arl);
        } else {
            System.out.println("Salud: " + salud);
            System.out.println("Pensión: " + pension);
            System.out.println("ARL: " + arl);
        }
        System.out.println("Total Descuentos: " + Descuentos);
        System.out.println("Total a pagar: " + pay);
        System.out.println("*************************************************");
        System.out.println("************ FIN DEL VOLANTE DE PAGO ************");
        System.out.println("*************************************************");
        Save(name, lastname, clocks, salary, clocks_ex, allpayEx, salud, pension, arl, Descuentos, pay);
        pause();
    }

    static void information(int options) {
        clr();
        System.out.print("nombre del trabajador:");
        leer.nextLine();
        name = leer.nextLine();
        System.out.print("apellido del trabajador:");
        lastname = leer.nextLine();
        clr();
        if (options == 1) {
            while (job == 0) {
                System.out.println("titulo de trabajo(ingrese el numero):");
                System.out.println("1)Auxiliar Administrativo");
                job = leer.nextInt();
                switch (job) {
                    case 1:
                        break;
                    default:
                        job = 0;
                        System.out.println("titulo de trabajo desconocido:");
                        pause();
                        clr();
                        break;
                }
            }

            System.out.println("horas trabajadas (mes)");
            clockswork = leer.nextInt();
            System.out.println("horas extras trabajadas (mes)");
            clockworkex = leer.nextInt();
        }
        if (options == 2) {
            while (job == 0) {
                System.out.println("titulo de trabajo(ingrese el numero):");
                System.out.println("1)Conductor");
                System.out.println("2)Oficios Generales");
                System.out.println("3)Vigilancia");
                job = leer.nextInt();
                switch (job) {
                    case 1:
                        clockswork = 160;
                        break;
                    case 2:
                        clockswork = 100;
                        break;
                    case 3:
                        clockswork = 336;
                        break;
                    default:
                        job = 0;
                        System.out.println("titulo de trabajo desconocido:");
                        pause();
                        clr();
                        break;
                }
            }
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
        salaryneto = salarybruto - salud - pension - arl + htex;
        sp = salud + pension;
        imprimir(name, lastname, clockswork, formatea.format(salarybruto), clockworkex, formatea.format(htex),
                formatea.format(salud), formatea.format(pension), formatea.format(arl), formatea.format(sp),
                formatea.format(salaryneto));
        cleanVariable();
        clr();
    }

    static double ARL(int type_job, int job, double salary) {
        if (type_job == 1) {
            App.Work = "Administrativo";
            App.type_job = "Auxiliar Administrativo";
            return 0.00522 * salary;
        } else {
            App.Work = "Operativo";
            if (job == 1) {
                App.type_job = "Oficios Generales";
                return 0.00522 * (salary * 0.4);
            }
            if (job == 2) {
                App.type_job = "Conductor";
                return 0.01044 * (salary * 0.4);
            }
            if (job == 3) {
                App.type_job = "Vigilancia";
                return 0.0435 * (salary * 0.4);
            }
            return 0;
        }
    }

    static int Salary(int type_job, int clocks) {
        if (type_job == 1) {
            return clocks * 20000;
        }
        if (type_job == 2) {
            return clocks * 40000;
        }
        return 0;
    }

    static void clr() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
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
            return 0.125 * (salary * 0.4);
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

    static void pause() {
        System.out.print("Presiona Enter para continuar...");
        leer.nextLine(); // Espera a que el usuario presione Enter
        leer.nextLine(); // Espera a que el usuario presione Enter
    }

    static double pension(int type_job, double salary) {
        if (type_job == 1) {
            return 0.04 * salary;
        } else {
            return 0.125 * (salary * 0.4);
        }
    }

    static void Save(String name, String lastname, int clocks, String salary, int clocks_ex, String allpayEx,
            String salud, String pension, String arl, String Descuentos, String pay) {

        try {
            FileWriter escritor = new FileWriter("table.txt", true);
            BufferedWriter bw = new BufferedWriter(escritor);
            bw.write(
                    "| " + name + " " + lastname + " | " + App.Work + " | " + type_job + " | " + clocks + " | " + salary
                            + " | "
                            + clocks_ex + " | " + allpayEx + " | " + salud + " | " + pension + " | " + arl + " | " + pay
                            + " |\n");
            bw.write(
                    "| ------------------------- | -------------- | ----------------------- | --- | ------- | --- | ------ | ------ | ------- | ---- | ------------- |\n");
            bw.close();
            System.out.println("Se ha guardado la información en el archivo.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar el archivo: " + e.getMessage());
        }

    }

    static void registorprint() {
        try {
            FileReader lector = new FileReader("table.txt");
            int caracter;
            while ((caracter = lector.read()) != -1) {
                System.out.print((char) caracter);
            }
            pause();
            lector.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
        }
    }

}
