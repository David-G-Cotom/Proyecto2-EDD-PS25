/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

import com.mycompany.proyecto2_edd_ps25.utils.Utilities;

/**
 *
 * @author Carlos Cotom
 */
public class Vehicle {
    
    private VehicleType vehicleType;
    private String plate;
    private String intersectionOrigin;
    private String destinationIntersection;
    private int totalPriority;
    private int waitingTime;
    private int urgencyLevel;
    private int[] direction;
    private final Utilities utilities;
    private boolean isAtDestination;

    public Vehicle(VehicleType vehicleType, String plate, String IntersectionOrigin, String destinationIntersection, int waitingTime, int urgencyLevel) {
        this.vehicleType = vehicleType;
        this.plate = plate;
        this.intersectionOrigin = IntersectionOrigin;
        this.destinationIntersection = destinationIntersection;
        this.waitingTime = waitingTime;
        this.urgencyLevel = urgencyLevel;
        this.direction = new int[2];
        this.utilities = new Utilities();
        this.setDirectionMovement();
        this.getFullPriority();
        this.isAtDestination = false;
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
        return intersectionOrigin;
    }

    public void setIntersectionOrigin(String intersectionOrigin) {
        this.intersectionOrigin = intersectionOrigin;
    }

    public String getDestinationIntersection() {
        return destinationIntersection;
    }

    public void setDestinationIntersection(String destinationIntersection) {
        this.destinationIntersection = destinationIntersection;
    }

    public int getTotalPriority() {
        return totalPriority;
    }

    public void setTotalPriority(int totalPriority) {
        this.totalPriority = totalPriority;
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

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    public boolean getIsAtDestination() {
        this.isAtDestination = this.direction[0] == 0 && this.direction[1] == 0;
        return this.isAtDestination;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "vehicleType=" + vehicleType + ", plate=" + plate + ", IntersectionOrigin=" + intersectionOrigin + ", destinationIntersection=" + destinationIntersection + ", waitingTime=" + waitingTime + ", urgencyLevel=" + urgencyLevel + '}';
    }
    
    private void setDirectionMovement() {
        int[] originCoordinates = this.utilities.convertCoordinate(this.intersectionOrigin);
        int[] destinationCoordinates = this.utilities.convertCoordinate(this.destinationIntersection);
        this.direction[0] = destinationCoordinates[0] - originCoordinates[0];
        this.direction[1] = destinationCoordinates[1] - originCoordinates[1];
    }
    
    private void getFullPriority() {
        this.totalPriority += this.vehicleType.getPriority();
        this.totalPriority += this.urgencyLevel;
        this.totalPriority += this.waitingTime;
        this.totalPriority += this.direction[0];
        this.totalPriority += this.direction[1];
    }
    
}
