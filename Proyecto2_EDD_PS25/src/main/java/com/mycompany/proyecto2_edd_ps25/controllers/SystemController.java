/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.controllers;

import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import com.mycompany.proyecto2_edd_ps25.structs.queue.PriorityQueue;
import com.mycompany.proyecto2_edd_ps25.utils.CSVReader;
import com.mycompany.proyecto2_edd_ps25.utils.Posters;

/**
 *
 * @author Carlos Cotom
 */
public class SystemController {
    
    private final Posters posters;
    private PriorityQueue<Vehicle> vehicles;

    public SystemController() {
        this.posters = new Posters();
        this.vehicles = new PriorityQueue<>();
    }
    
    private void enterVechicle() {
        VehicleType vehicleType = this.posters.incomeType();
        String plate = this.posters.entrancePlate();
        String origin = this.posters.incomeSource();
        String destination = this.posters.destinationIncome();
        int priority = this.posters.priorityEntry();
        int waitingTime = this.posters.incomeWaitingTime();
        Vehicle newVechicle = new Vehicle(vehicleType, plate, origin, destination, priority, waitingTime, 0);
        this.vehicles.enqueue(newVechicle, priority);
        System.out.println("Vehiculo Registrado en el Sistema");
    }
    
    public void processVehicle() {
        
    }
    
    public void execute() {
        int option = this.posters.mainMenu();
        switch (option) {
            case 1 -> this.start();
            case 2 -> System.out.println("Fin de la Simulacion");
        }
    }
    
    private void start() {
        int option = this.posters.menuOrderVehicles();
        switch (option) {
            case 1 -> this.enterVechicle();
            case 2 -> CSVReader.readTrafficFile("trafico.csv", this.vehicles);
        }
        
    }
    
    public void registerEvent() {
        
    }
    
    public void generateReports() {
        
    }
    
    public void showMenu() {
        
    }
    
}
