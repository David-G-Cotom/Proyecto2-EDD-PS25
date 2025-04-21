/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

/**
 *
 * @author Carlos Cotom
 */
public class Vehicle {
    
    private VehicleType vehicleType;
    private String plate;
    private String IntersectionOrigin;
    private String destinationIntersection;
    private int priority;
    private int waitingTime;
    private int urgencyLevel;

    public Vehicle(VehicleType vehicleType, String plate, String IntersectionOrigin, String destinationIntersection, int priority, int waitingTime, int urgencyLevel) {
        this.vehicleType = vehicleType;
        this.plate = plate;
        this.IntersectionOrigin = IntersectionOrigin;
        this.destinationIntersection = destinationIntersection;
        this.priority = priority;
        this.waitingTime = waitingTime;
        this.urgencyLevel = urgencyLevel;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getIntersectionOrigin() {
        return IntersectionOrigin;
    }

    public void setIntersectionOrigin(String IntersectionOrigin) {
        this.IntersectionOrigin = IntersectionOrigin;
    }

    public String getDestinationIntersection() {
        return destinationIntersection;
    }

    public void setDestinationIntersection(String destinationIntersection) {
        this.destinationIntersection = destinationIntersection;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "vehicleType=" + vehicleType + ", plate=" + plate + ", IntersectionOrigin=" + IntersectionOrigin + ", destinationIntersection=" + destinationIntersection + ", priority=" + priority + ", waitingTime=" + waitingTime + ", urgencyLevel=" + urgencyLevel + '}';
    }
    
}
