/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.utils;

import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import com.mycompany.proyecto2_edd_ps25.structs.queue.Queue;
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
    
    public static Queue<Vehicle> readTrafficFile(String filePath) {
        Queue<Vehicle> vehicles = new Queue<>();
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
                        vehicles.enqueue(newVechicle);
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato numerico en linea: " + line + " -> " + e.getMessage());
                    }
                } else {
                    System.out.println("Linea con formato incorrecto, ignorada: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return vehicles;
    }
    
}
