/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

import com.mycompany.proyecto2_edd_ps25.structs.queue.NodeQueue;
import com.mycompany.proyecto2_edd_ps25.structs.queue.PriorityQueue;

/**
 *
 * @author Carlos Cotom
 */
public class Intersection {
    
    private String id;
    private int complexity;
    private PriorityQueue<Vehicle> vehiclesWaiting;
    private IntersectionComplexityType intersectionType;
    private boolean checkpoint;
    private int numberOfVehiclesCrossed;

    public Intersection(String id) {
        this.id = id;
        IntersectionComplexityType[] values = IntersectionComplexityType.values();
        this.intersectionType = values[(int) (Math.random() * values.length)];
        this.complexity = this.intersectionType.getPriority();
        this.vehiclesWaiting = new PriorityQueue<>();
        this.initializeCheckpoint();
        this.numberOfVehiclesCrossed = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public PriorityQueue<Vehicle> getVehiclesWaiting() {
        return vehiclesWaiting;
    }

    public void setVehiclesWaiting(PriorityQueue<Vehicle> vehiclesWaiting) {
        this.vehiclesWaiting = vehiclesWaiting;
    }

    public IntersectionComplexityType getIntersectionType() {
        return intersectionType;
    }

    public void setIntersectionType(IntersectionComplexityType intersectionType) {
        this.intersectionType = intersectionType;
    }

    public boolean isCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(boolean checkpoint) {
        this.checkpoint = checkpoint;
    }

    public int getNumberOfVehiclesCrossed() {
        return numberOfVehiclesCrossed;
    }

    public void setNumberOfVehiclesCrossed(int numberOfVehiclesCrossed) {
        this.numberOfVehiclesCrossed = numberOfVehiclesCrossed;
    }

    @Override
    public String toString() {
        return "Intersection{" + "id=" + id + ", complexity=" + complexity + '}';
    }
    
    public void incraseComplexity(Vehicle newVehicle) {
        this.vehiclesWaiting.enqueue(newVehicle, newVehicle.getTotalPriority());
        this.complexity++;
        this.numberOfVehiclesCrossed++;
    }
    
    public Vehicle reduceComplexity() {
        this.complexity--;
        return this.vehiclesWaiting.unqueue();
    }
    
    public void printIntersection() {
        System.out.println("Interseccion: " + this.id);
        System.out.println("\tComplejidad: " + this.complexity);
        System.out.println("\tTipo de Interseccion: " + this.intersectionType.toString());
        System.out.println("\tVehiculos en Espera:");
        NodeQueue<Vehicle> vehicles = this.vehiclesWaiting.getHead();
        while (vehicles != null) {
            System.out.println("\t\t" + vehicles.getData().toString());
            vehicles = vehicles.getNext();
        }
    }
    
    private void initializeCheckpoint() {
        int random = (int) (Math.random() * 10);
        this.checkpoint = random < 2;
    }
    
}
