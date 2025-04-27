/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.utils;

import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import com.mycompany.proyecto2_edd_ps25.structs.queue.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Carlos Cotom
 */
public class CSVReader {

    private static final String SEPARATOR = ",";
    private static final int NUMBER_FIELDS = 6;

    public static void readTrafficFile(String filePath, PriorityQueue<Vehicle> vehicles) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //CONSIDERAR SI HAY ENCABEZADO
            while ((line = br.readLine()) != null) {
                String[] data = line.split(SEPARATOR);
                if (data.length == NUMBER_FIELDS) {
                    try {
                        VehicleType vehicleType = VehicleType.valueOf(data[0].trim());
                        String plate = data[1].trim();
                        String origin = data[2].trim();
                        String destination = data[3].trim();
                        int priority = Integer.parseInt(data[4].trim());
                        int waitingTime = Integer.parseInt(data[5].trim());
                        Vehicle newVechicle = new Vehicle(vehicleType, plate, origin, destination, priority, waitingTime, 0);
                        vehicles.enqueue(newVechicle, priority);
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato numerico en linea: " + line + " -> ignorada");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error de formato para tipo de vehiculo en linea: " + line + " -> ignorada");
                    }
                } else {
                    System.out.println("Linea con formato incorrecto, ignorada: " + line);
                }
            }
            System.out.println("Datos Registrados en el Sistema");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

}
