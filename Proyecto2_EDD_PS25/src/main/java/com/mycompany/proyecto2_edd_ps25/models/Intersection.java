/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

import com.mycompany.proyecto2_edd_ps25.structs.matrix.IntersectionComplexityType;
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

    public Intersection(String id) {
        this.id = id;
        IntersectionComplexityType[] values = IntersectionComplexityType.values();
        this.intersectionType = values[(int) (Math.random() * values.length)];
        this.complexity = this.intersectionType.getPriority();
        this.vehiclesWaiting = new PriorityQueue<>();
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

    @Override
    public String toString() {
        return "Intersection{" + "id=" + id + ", complexity=" + complexity + '}';
    }
    
    public void incraseComplexity(Vehicle newVehicle) {
        this.vehiclesWaiting.enqueue(newVehicle, newVehicle.getTotalPriority());
        this.complexity++;
    }
    
    public void reduceComplexity() {
        this.vehiclesWaiting.unqueue();
        this.complexity--;
    }
    
}
