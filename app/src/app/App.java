/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

// The above code is importing necessary Java libraries for formatting numbers and reading/writing
// files. It is also declaring a scanner object to read user input. The pound signs ("
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

    // The above code is declaring and initializing variables and objects in Java.
    static NumberFormat formatea = NumberFormat.getCurrencyInstance((new Locale("es", "CO")));
    static Scanner leer = new Scanner(System.in);
    static String name, lastname, type_job, Work, nametable = "Table";
    static int clockswork = 0, clockworkex = 0, job = 0;
    static double health = 0, pension = 0, salarybruto = 0, arl = 0, salaryneto = 0, overtimePay = 0, hpp = 0;
    static protected int options, save;

    /**
     * @param args the command line arguments
     */
    /**
     * This is a Java program that displays a menu with options to add employee
     * payroll information,
     * print payroll, insert a payroll file, create a payroll file, and exit the
     * program.
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

    /**
     * This function prints out a payroll statement and gives the option to save the
     * information to a
     * table.
     * 
     * @param name       A string representing the name of the employee.
     * @param lastname   The parameter "lastname" is a String variable that
     *                   represents the last name of an
     *                   employee.
     * @param clocks     The number of hours worked in a month.
     * @param salary     The gross salary of the employee.
     * @param clocks_ex  The number of extra hours worked by the employee.
     * @param allpayEx   The total payment for extra hours worked.
     * @param health     The health discount percentage applied to the employee's
     *                   salary as part of the
     *                   mandatory deductions required by law.
     * @param pension    The amount of money deducted from an employee's salary as a
     *                   contribution to a pension
     *                   plan or retirement savings account.
     * @param arl        ARL stands for "Administradora de Riesgos Laborales" which
     *                   is a mandatory insurance in
     *                   Colombia that covers work-related accidents and illnesses.
     *                   In the context of the code, it refers to
     *                   the percentage of the employee's salary that is deducted
     *                   for this insurance.
     * @param Descuentos "Descuentos" is a String variable that holds the total
     *                   amount of deductions from
     *                   an employee's salary, including health insurance, pension,
     *                   and ARL (Accidents at Work and
     *                   Occupational Diseases) contributions.
     * @param pay        The total amount of money to be paid to the employee after
     *                   all deductions and additions
     *                   have been made.
     */
    static void print(String name, String lastname, int clocks, String salary, int clocks_ex, String allpayEx,
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

    /**
     * The function "information" takes user input for a worker's name, job title,
     * hours worked, and
     * calculates their gross and net salary, as well as deductions for health,
     * pension, and ARL.
     * 
     * @param options An integer value that determines the type of job and the
     *                calculations to be performed
     *                for the employee's salary.
     */
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
                System.out.println("1)Oficios Generales");
                System.out.println("2)Conductor");
                System.out.println("3)Vigilancia");
                job = leer.nextInt();
                switch (job) {
                    case 1:
                        clockswork = 100;
                        break;
                    case 2:
                        clockswork = 160;
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
        overtimePay = clock_expay(options, clockworkex);
        salarybruto = Salary(options, clockswork);
        if (clockworkex == 0) {
            health = hearthand(options, salarybruto);
            pension = pension(options, salarybruto);
            arl = ARL(options, job, salarybruto);
        }
        if (clockworkex > 0) {
            health = hearthand(options, salarybruto + overtimePay);
            pension = pension(options, salarybruto + overtimePay);
            arl = ARL(options, job, salarybruto + overtimePay);
        }
        salaryneto = salarybruto - health - pension - arl + overtimePay;
        hpp = health + pension;
        print(name, lastname, clockswork, formatea.format(salarybruto), clockworkex, formatea.format(overtimePay),
                formatea.format(health), formatea.format(pension), formatea.format(arl), formatea.format(hpp),
                formatea.format(salaryneto));
        cleanVariable();
        clr();
    }

    /**
     * The function calculates the ARL (Average Risk Level) based on the type of
     * job, job position, and
     * salary.
     * 
     * @param type_job an integer representing the type of job (1 for
     *                 administrative, 2 for operative)
     * @param job      The job parameter is an integer that represents the
     *                 especific
     *                 job position within the
     *                 "Operativo" category. It can have one of three values: 1 for
     *                 "Oficios Generales", 2 for
     *                 "Conductor", and 3 for "Vigilancia".
     * @param salary   The salary of the employee.
     * @return a double value which represents the ARL (Administrative Risk
     *         Insurance) contribution
     *         based on the type of job, job position and salary. The value returned
     *         depends on the conditions
     *         especified in the if-else statements.
     */
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

    /**
     * The function calculates the salary based on the type of job and the number of
     * clocks worked.
     * 
     * @param type_job An integer representing the type of job. It can be either 1
     *                 or 2.
     * @param clocks   The number of hours worked by the employee.
     * @return The function `Salary` returns the salary of an employee based on
     *         their job type and the
     *         number of clocks they have worked. If the job type is 1, the function
     *         returns the salary calculated
     *         by multiplying the number of clocks by 20000. If the job type is 2,
     *         the function returns the salary
     *         calculated by multiplying the number of clocks by 40000. If the job
     *         type is
     */
    static int Salary(int type_job, int clocks) {
        if (type_job == 1) {
            return clocks * 20000;
        }
        if (type_job == 2) {
            return clocks * 40000;
        }
        return 0;
    }

    /**
     * The function clears the console by printing 50 empty lines in Java.
     */
    static void clr() {
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
    }

    /**
     * This function creates a table in a text file for storing employee payroll
     * information.
     */
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
        clr();
        System.out.print("archivo Creado\n");
        System.out.print("todo registro que se cree apartir de ahora se guardara en este archivo:" + nametable + "\n");
        System.out.print("para guardar en otro archivo tiene que crear un archivo nuevo por el menu\n");
        pause();
        clr();
    }

    /**
     * The function calculates the total payment for a job based on the number of
     * clocks and the type of
     * job.
     * 
     * @param type_job An integer representing the type of job. It is expected to be
     *                 either 1 or any
     *                 other integer value.
     * @param clocks   The number of clock cycles required for a particular job.
     * @return If the `type_job` parameter is equal to 1, the function returns the
     *         product of `clocks`
     *         and 25000. Otherwise, it returns 0.
     */
    static int clock_expay(int type_job, int clocks) {
        if (type_job == 1) {
            return clocks * 25000;
        }
        return 0;
    }

    /**
     * The function calculates the amount of heart-hand bonus based on the type of
     * job and salary.
     * 
     * @param type_job An integer representing the type of job. If it is equal to 1,
     *                 it means the job is
     *                 a full-time job. If it is not equal to 1, it means the job is
     *                 a part-time job.
     * @param salary   The salary parameter is a double data type representing the
     *                 amount of money earned
     *                 by an employee.
     * @return The function `hearthand` is returning a double value which is either
     *         4% of the salary if
     *         the `type_job` is 1, or 12.5% of 40% of the salary if the `type_job`
     *         is not 1.
     */
    static double hearthand(int type_job, double salary) {
        if (type_job == 1) {
            return 0.04 * salary;
        } else {
            return 0.125 * (salary * 0.4);
        }
    }

    /**
     * The function cleans/reset all the variables in the App class to their default
     * values.
     */
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
        App.overtimePay = 0;
        App.hpp = 0;
    }

    /**
     * The function "pause" prompts the user to press Enter and waits for the input
     * before continuing.
     */
    static void pause() {
        System.out.print("Presiona Enter para continuar...");
        leer.nextLine(); // Espera a que el usuario presione Enter
    }

    /**
     * The function calculates the pension amount based on the type of job and
     * salary.
     * 
     * @param type_job An integer representing the type of job. If it is equal to 1,
     *                 it means the job is a
     *                 government job. If it is any other integer, it means the job
     *                 is not a government job.
     * @param salary   The salary is a double data type and represents the amount of
     *                 money earned by an
     *                 employee. It is one of the parameters passed to the pension
     *                 function.
     * @return The function `pension` returns a double value which represents the
     *         pension amount based on
     *         the type of job and salary provided as input parameters. If the type
     *         of job is 1, it returns 4% of
     *         the salary, otherwise it returns 12.5% of 40% of the salary.
     */
    static double pension(int type_job, double salary) {
        if (type_job == 1) {
            return 0.04 * salary;
        } else {
            return 0.16 * (salary * 0.4);
        }
    }

    /**
     * The function saves employee information to a text file in a formatted manner.
     * 
     * @param name       The first name of the employee.
     * @param lastname   The last name of the employee.
     * @param clocks     It is likely referring to the number of hours worked by an
     *                   employee, as it is being
     *                   passed as an integer parameter in the Save() method along
     *                   with other employee information such as
     *                   name, salary, and deductions.
     * @param salary     The salary parameter is a String that represents the base
     *                   salary of an employee.
     * @param clocks_ex  The parameter "clocks_ex" is an integer that represents the
     *                   number of extra hours
     *                   worked by an employee.
     * @param allpayEx   It is a String variable that represents the total amount of
     *                   extra pay received by an
     *                   employee, including overtime and bonuses.
     * @param health     The health parameter is a String that represents the amount
     *                   of money deducted from an
     *                   employee's salary for health insurance.
     * @param pension    The parameter "pension" is a String variable that
     *                   represents the amount of money
     *                   deducted from an employee's salary for their pension plan.
     * @param arl        ARL stands for "Administradora de Riesgos Laborales" which
     *                   is a mandatory insurance in
     *                   Colombia that covers work-related accidents and illnesses.
     *                   It is one of the deductions that may be
     *                   made from an employee's salary.
     * @param Descuentos "Descuentos" is a parameter that represents the total
     *                   amount of deductions made
     *                   from an employee's salary, such as taxes, social security
     *                   contributions, and other withholdings.
     * @param pay        The parameter "pay" is a String variable that represents
     *                   the total payment received by an
     *                   employee, including all the deductions and bonuses.
     */
    static void Save(String name, String lastname, int clocks, String salary, int clocks_ex, String allpayEx,
            String health, String pension, String arl, String Descuentos, String pay) {
        try {
            FileWriter write = new FileWriter(nametable + ".txt", true);
            BufferedWriter bw = new BufferedWriter(write);
            String formattedData = String.format("%-25s%-15s%-35s%-15s%-20s%-15s%-15s%-15s%-15s%-15s%-20s%n",
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

    /**
     * This function reads and prints the contents of a text file with a given name,
     * or prints "Vacio"
     * if the name is "Table".
     */
    static void Logprint() {
        if (nametable != "Table") {
            try {
                FileReader reader = new FileReader(nametable + ".txt");
                int caracter;
                while ((caracter = reader.read()) != -1) {
                    System.out.print((char) caracter);
                }
                leer.nextLine();
                reader.close();
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Vacio");
            leer.nextLine();
        }
        pause();
    }

    /**
     * This function prompts the user to enter the name of an existing payroll file
     * and attempts to read
     * it, displaying an error message if it fails.
     */
    static void SearchFile() {
        System.out.print("ingrese el nombre de un archivo de nomina existente: ");
        App.nametable = leer.nextLine();
        App.nametable = leer.nextLine();
        try {
            FileReader reader = new FileReader(nametable + ".txt");
            reader.close();
            pause();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
            App.nametable = "";
            pause();
        }
    }
}