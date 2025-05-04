/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.utils;

import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Carlos Cotom
 */
public class Utilities {

    private static final String SEPARATOR = ",";
    private static final int NUMBER_FIELDS = 6;

    public Utilities() {
    }

    public void readTrafficFile(String filePath, LinkedList<Vehicle> vehicles, LinkedList<int[]> coordinates) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //CONSIDERAR SI HAY ENCABEZADO
            while ((line = br.readLine()) != null) {
                String[] data = line.split(SEPARATOR);
                if (data.length == NUMBER_FIELDS) {
                    try {
                        VehicleType vehicleType = VehicleType.valueOf(data[0].trim().toUpperCase());
                        String plate = data[1].trim().toUpperCase();
                        if (!this.validPlate(plate)) {
                            System.out.println("Error en formato de Placa linea: " + line + " -> ignorada");
                            continue;
                        }
                        String origin = data[2].trim().toUpperCase();
                        String destination = data[3].trim().toUpperCase();
                        int priority = Integer.parseInt(data[4].trim());
                        if (priority < 1 && priority > 5) {
                            System.out.println("Error en valor de Prioridad linea: " + line + " -> ignorada");
                            continue;
                        }
                        int waitingTime = Integer.parseInt(data[5].trim());
                        Vehicle newVechicle = new Vehicle(vehicleType, plate, origin, destination, waitingTime, priority);
                        vehicles.addElementAt(newVechicle, "");
                        int[] originCoordinates = this.convertCoordinate(origin);
                        int[] destinationCoordinates = this.convertCoordinate(destination);
                        coordinates.addElementAt(originCoordinates, "");
                        coordinates.addElementAt(destinationCoordinates, "");
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato numerico en linea: " + line + " -> ignorada");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error de formato para tipo de vehiculo en linea: " + line + " -> ignorada");
                    }
                } else {
                    System.out.println("Linea con formato incorrecto porque Faltan Datos: " + line + " -> ignorada");
                }
            }
            System.out.println("Se Registraron " + vehicles.getSize() + " Vehiculos en el Sistema\n");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
    
    public int[] convertCoordinate(String value) {
        int[] dimensions = new int[2];
        int letterDimensions = 26;
        String number = "";
        for (int i = 0; i < value.length(); i++) {
            if (Character.isLetter(value.charAt(i))) {
                letterDimensions *= i;
                dimensions[0] = value.charAt(i) - 'A' + 1 + letterDimensions;
            }
            if (Character.isDigit(value.charAt(i))) {
                number += value.charAt(i);
            }
        }
        dimensions[1] = Integer.parseInt(number);
        return dimensions;
    }
    
    public String convertCoordinate(int value) {
        StringBuilder result = new StringBuilder();
        int letters = 26;
        int dimensions = value / letters;
        for (int i = 0; i < dimensions; i++) result.append("A");
        
        int residue = value % letters;
        char c = (char) ('A' + residue);
        result.append(c);
        return result.toString();
    }
    
    public boolean validPlate(String plate) {
        if (plate.length() != 8) return false;
        
        int i = 1;
        for (char c : plate.toCharArray()) {
            if (i >= 1 && i <= 3) {
                if (!Character.isLetter(c)) return false;
            }
            if (i == 8) {
                if (!Character.isLetter(c)) return false;
            }
            if (i >= 5 && i <= 7) {
                if (!Character.isDigit(c)) return false;
            }
            if (i == 4) {
                if (c != '-') return false;
            }
            i++;
        }
        return true;
    }

}
