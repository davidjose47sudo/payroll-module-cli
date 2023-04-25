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
import java.io.PrintWriter;

/**
 *
 * @author ADSI
 */
public class App {

    static NumberFormat formatea = NumberFormat.getCurrencyInstance((new Locale("es", "CO")));
    static Scanner leer = new Scanner(System.in);
    static String name, lastname, type_job, Work, nametable = "Table";
    static int clockswork = 0, clockworkex = 0, job = 0;
    static double health = 0, pension = 0, salarybruto = 0, arl = 0, salaryneto = 0, htex = 0, sp = 0;
    static protected int options, save;

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
            System.out.println("4)insertar un archivo de Nomina");
            System.out.println("5)crear archivo para Nomina");
            System.out.println("6)Salir");
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
                    Logprint();
                    break;
                case 4:
                    SearchFile();
                    break;
                case 5:
                    createTable();
                    break;
                case 6:
                    menu = false;
                    break;
                default:
                    System.out.println("opcion no valida");
                    pause();
            }
        }
        clr();
        System.out.print("-------------------\n");
        System.out.print("|See you next time|\n");
        System.out.print("-------------------\n");
        leer.nextLine();
        pause();
    }

    static void imprimir(String name, String lastname, int clocks, String salary, int clocks_ex, String allpayEx,
            String health, String pension, String arl, String Descuentos, String pay) {
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
            System.out.println("salud(4%): " + health);
            System.out.println("Pensión(4%): " + pension);
            System.out.println("ARL(0,522%): " + arl);
        } else {
            System.out.println("salud: " + health);
            System.out.println("Pensión: " + pension);
            System.out.println("ARL: " + arl);
        }
        System.out.println("Total Descuentos: " + Descuentos);
        System.out.println("Total a pagar: " + pay);
        System.out.println("*************************************************");
        System.out.println("************ FIN DEL VOLANTE DE PAGO ************");
        System.out.println("*************************************************\n");
        System.out.println("Quieres Guardar el Registro?");
        System.out.println("1)si\n2)no");
        save = leer.nextInt();
        if (save == 1) {
            if (nametable == "Table" || nametable == "") {
                System.out.println("No Hay archivo Creado, Por favor crearlo");
                createTable();
            }
            Save(name, lastname, clocks, salary, clocks_ex, allpayEx, health, pension, arl, Descuentos, pay);
        }
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
            health = hearthand(options, salarybruto);
            pension = pension(options, salarybruto);
            arl = ARL(options, job, salarybruto);
        }
        if (clockworkex > 0) {
            health = hearthand(options, salarybruto + htex);
            pension = pension(options, salarybruto + htex);
            arl = ARL(options, job, salarybruto + htex);
        }
        salaryneto = salarybruto - health - pension - arl + htex;
        sp = health + pension;
        imprimir(name, lastname, clockswork, formatea.format(salarybruto), clockworkex, formatea.format(htex),
                formatea.format(health), formatea.format(pension), formatea.format(arl), formatea.format(sp),
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

    static void createTable() {
        System.out.print("escriba el nombre del archivo de nomina(no utilizar '/'):");
        leer.nextLine();
        nametable = leer.nextLine();
        FileWriter file;
        try {
            file = new FileWriter(nametable + ".txt", true);
            PrintWriter out = new PrintWriter(file);
            out.printf("%-25s%-15s%-35s%-15s%-20s%-15s%-15s%-15s%-15s%-15s%-20s%n", "Nombre", "Tipo", "Cargo",
                    "HT",
                    "Salario", "HE", "TPHE", "Salud",
                    "Pension", "ARL", "Total a pagar");
            out.write(
                    "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("archivo Creado\n");
        System.out.print("todo registro que se cree apartir de ahora se guardara en este archivo:" + nametable + "\n");
        System.out.print("para guardar en otro archivo tiene que crear un archivo nuevo por el menu\n");

        pause();
        clr();
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
        App.health = 0;
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
    }

    static double pension(int type_job, double salary) {
        if (type_job == 1) {
            return 0.04 * salary;
        } else {
            return 0.125 * (salary * 0.4);
        }
    }

    static void Save(String name, String lastname, int clocks, String salary, int clocks_ex, String allpayEx,
            String health, String pension, String arl, String Descuentos, String pay) {

        try {
            FileWriter escritor = new FileWriter(nametable + ".txt", true);
            BufferedWriter bw = new BufferedWriter(escritor);
            String formattedData = String.format("%-25s%-15s%-35s%-15s%-20s%-15s%-15s%-15s%-15s%-20s%-15s%n",
                    name + " " +
                            lastname,
                    App.Work, type_job, clocks, salary, clocks_ex, allpayEx,
                    health, pension, arl, pay);
            bw.write(formattedData);
            bw.write(
                    "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            bw.close();
            System.out.println("Se ha guardado la información en el archivo.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar el archivo: " + e.getMessage());
        }

    }

    static void Logprint() {
        if (nametable != "Table") {
            try {
                FileReader lector = new FileReader(nametable + ".txt");
                int caracter;
                while ((caracter = lector.read()) != -1) {
                    System.out.print((char) caracter);
                }
                leer.nextLine();
                lector.close();
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Vacio");
            leer.nextLine();
        }
        pause();
    }

    static void SearchFile() {
        System.out.print("ingrese el nombre de un archivo de nomina existente: ");
        App.nametable = leer.nextLine();
        App.nametable = leer.nextLine();
        try {
            FileReader lector = new FileReader(nametable + ".txt");
            lector.close();
            pause();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
            App.nametable = "";
            pause();
        }
    }
}
