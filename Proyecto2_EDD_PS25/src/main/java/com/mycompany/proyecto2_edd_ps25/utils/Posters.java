/*
 * Click nbfs-//nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs-//nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.utils;

import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import java.util.Scanner;

/**
 *
 * @author Carlos Cotom
 */
public class Posters {
    
    private final Utilities utilities;
    private final Scanner scanner;

    public Posters() {
        scanner = new Scanner(System.in);
        this.utilities = new Utilities();
    }

    public int mainMenu() {
        int option;
        while (true) {
            System.out.println("------------------ MENU PRINCIPAL ------------------");
            System.out.println("                     1.Iniciar                      ");
            System.out.println("                     2.Salir                        ");
            System.out.println("----------------------------------------------------");
            System.out.print("Ingrese su opcion aqui: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                this.warningNumericFormat();
                continue;
            }
            if (option != 1 && option != 2) {
                System.out.println("Opcion Invalida!!!");
                continue;
            }
            break;
        }
        return option;
    }

    public void initialConfiguration() {
        System.out.println("------------------ CONFIGURACION INICIAL ------------------");
        System.out.println("               Ingreso automatico de archivo               ");
        System.out.println("                           NOTA:                           ");
        System.out.println("   Asegurese que el archivo tenga el nombre trafico.csv    ");
        System.out.println("-----------------------------------------------------------");
        System.out.print("Click para continuar: ");
        scanner.nextLine();
    }

    public VehicleType incomeType() {
        VehicleType vehicleType;
        while (true) {
            System.out.println("-------------------- VEHICULO --------------------");
            System.out.println("          Ingrese el tipo de su vehiculo          ");
            System.out.println("   (AMBULANCIA, POLICIA, PARTICULAR, TRANSPORTE)  ");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese el tipo aqui: ");
            try {
                vehicleType = VehicleType.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                this.vehicleTypeWarning();
            }
        }
        return vehicleType;
    }

    private void vehicleTypeWarning() {
        System.out.println("------------------ ADVERTENCIA! ------------------");
        System.out.println("     El tipo de vehiculo ingresado no es valido   ");
        System.out.println("               (Vuelva a intentarlo)              ");
        System.out.println("--------------------------------------------------");
    }

    public String entrancePlate() {
        String plate;
        while (true) {
            System.out.println("-------------------- VEHICULO --------------------");
            System.out.println("           Ingrese la placa de su vehiculo        ");
            System.out.println("     Formato de Ejemplo: AAA-000A (8 caracteres)  ");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese la placa aqui: ");
            plate = scanner.nextLine().toUpperCase();
            if (this.utilities.validPlate(plate)) break;
            
            this.plateFormatWarning();
        }
        return plate;
    }

    public void plateFormatWarning() {
        System.out.println("-------------------- ADVERTENCIA! --------------------");
        System.out.println("   El numero de placa recomendado es el siguiente:    ");
        System.out.println("               AAA-000A (8 caracteres)                ");
        System.out.println("                (Vuelva a intentarlo)                 ");
        System.out.println("------------------------------------------------------");
    }

    public String incomeSource() {
        String origin;
        while (true) {
            System.out.println("-------------------- VEHICULO --------------------");
            System.out.println("          Ingrese el origen del vehiculo          ");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese el origen aqui: ");
            origin = scanner.nextLine().toUpperCase();
            if (this.isValidIntersection(origin)) {
                break;
            }
        }
        return origin;
    }

    public String destinationIncome() {
        String destination;
        while (true) {
            System.out.println("-------------------- VEHICULO --------------------");
            System.out.println("          Ingrese el destino del vehiculo         ");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese el origen aqui: ");
            destination = scanner.nextLine().toUpperCase();
            if (this.isValidIntersection(destination)) {
                break;
            }
        }
        return destination;
    }
    
    private boolean isValidIntersection(String intersection) {
        boolean validIntersection = true;
        boolean previousIsLetter = true;
        int changesLetterToNumber = 0;
        
        for (char c : intersection.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                validIntersection = false;
                break;
            }
            if (Character.isLetter(c) && !previousIsLetter) {
                changesLetterToNumber++;
                previousIsLetter = true;
            }
            if (Character.isDigit(c) && previousIsLetter) {
                changesLetterToNumber++;
                previousIsLetter = false;
            }
        }
        if (!validIntersection
                || changesLetterToNumber > 1
                || intersection.length() < 2) {
            this.intersectionFormatWarning();
            return false;
        }
        return true;
    }

    private void intersectionFormatWarning() {
        System.out.println("-------------------- ADVERTENCIA! --------------------");
        System.out.println("       La interseccion debe de tener el formato:      ");
        System.out.println("                     LetraNumero                      ");
        System.out.println("                (Vuelva a intentarlo)                 ");
        System.out.println("------------------------------------------------------");
    }

    public int priorityEntry() {
        int priority;
        while (true) {
            System.out.println("-------------------- VEHICULO --------------------");
            System.out.println("            Ingrese la prioridad (1 - 5)          ");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese la prioridad del vehiculo aqui: ");
            try {
                priority = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                this.warningNumericFormat();
                continue;
            }
            if (priority < 1 || priority > 5) {
                System.out.println("Rango de Prioridad Invalido");
                continue;
            }
            break;
        }
        return priority;
    }

    public int incomeWaitingTime() {
        int waitingTime;
        while (true) {
            System.out.println("-------------------- VEHICULO --------------------");
            System.out.println("           Ingrese el tiempo de espera            ");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese el tiempo aqui: ");
            try {
                waitingTime = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                this.warningNumericFormat();
                continue;
            }
            break;
        }
        return waitingTime;
    }

    public void warningNumericFormat() {
        System.out.println("------------------ ADVERTENCIA! ------------------");
        System.out.println("          Debe ingresar un valor numerico         ");
        System.out.println("              (Vuelva a intentarlo)               ");
        System.out.println("--------------------------------------------------");
    }
    
    public int simulationMenu() {
        int option;
        while (true) {
            System.out.println("--------------- MENU DE SIMULACION ---------------");
            System.out.println("                 1.Manual                         ");
            System.out.println("                 2.Automatica                     ");
            System.out.println("                 3.Ingresar Vehiculo              ");
            System.out.println("                 4.Buscar Vehiculo                ");
            System.out.println("--------------------------------------------------");
            System.out.print("Ingrese su opcion aqui: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                this.warningNumericFormat();
                continue;
            }
            if (option < 1 || option > 4) {
                System.out.println("Opcion Invalida!!!");
                continue;
            }
            break;
        }
        return option;
    }
    
    public int menuViwReports() {
        int option;
        while (true) {
            System.out.println("---------- QUIERE VER REPORTES? ----------");
            System.out.println("                   1.Si                   ");
            System.out.println("                   2.No                   ");
            System.out.println("------------------------------------------");
            System.out.print("Ingrese la opcion aqui: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                this.warningNumericFormat();
                continue;
            }
            if (option != 1 && option != 2) {
                System.out.println("Opcion Invalida!!!");
                continue;
            }
            break;
        }
        return option;
    }
    
    public int reportsMenu() {
        int option;
        while (true) {
            System.out.println("--------------- REPORTES DE SIMULACION ---------------");
            System.out.println("            1.Ranking de vehiculos                    ");
            System.out.println("            2.Cantidad de Vehiculos que Cruzaron      ");
            System.out.println("            3.Tiempo Promedio de Espera               ");
            System.out.println("            4.Grafico de Intersecciones               ");
            System.out.println("            5.Placas Duplicadas o en Conflicto        ");
            System.out.println("            6.Registro de los Ultimos 20 Eventos      ");
            System.out.println("------------------------------------------------------");
            System.out.print("Ingrese su opcion aqui: ");
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                this.warningNumericFormat();
                continue;
            }
            if (!(option > 0 && option < 7)) {
                System.out.println("Opcion Invalida!!!");
                continue;
            }
            break;
        }
        return option;
    }

}
