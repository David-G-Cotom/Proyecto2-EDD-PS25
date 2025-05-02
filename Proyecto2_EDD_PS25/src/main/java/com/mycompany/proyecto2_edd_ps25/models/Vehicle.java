/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

import com.mycompany.proyecto2_edd_ps25.utils.Utilities;
import java.util.Arrays;

/**
 *
 * @author Carlos Cotom
 */
public class Vehicle {
    
    private VehicleType vehicleType;
    private String plate;
    private String intersectionOrigin;
    private String destinationIntersection;
    private int[] totalPriority;
    private int waitingTime;
    private int urgencyLevel;
    private int[] direction;
    private final Utilities utilities;
    private boolean isAtDestination;
    private int[] currentCoordinate;

    public Vehicle(VehicleType vehicleType, String plate, String intersectionOrigin, String destinationIntersection, int waitingTime, int urgencyLevel) {
        this.vehicleType = vehicleType;
        this.plate = plate;
        this.intersectionOrigin = intersectionOrigin;
        this.destinationIntersection = destinationIntersection;
        this.waitingTime = waitingTime;
        this.urgencyLevel = urgencyLevel;
        this.direction = new int[2];
        this.utilities = new Utilities();
        this.setDirectionMovement();
        //4 campos que evaluaran el orden de prioridad
        //Si se cambia el valor, cambiar/agregar tambien la constante en la PriorityQueue
        this.totalPriority = new int[4];
        this.getFullPriority();
        this.isAtDestination = false;
        this.currentCoordinate = this.utilities.convertCoordinate(intersectionOrigin);
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

    public int[] getTotalPriority() {
        return totalPriority;
    }

    public void setTotalPriority(int[] totalPriority) {
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

    public int[] getCurrentCoordinate() {
        return currentCoordinate;
    }

    public void setCurrentCoordinate(int[] currentCoordinate) {
        this.currentCoordinate = currentCoordinate;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "vehicleType=" + vehicleType + ", plate=" + plate + ", destinationIntersection=" + destinationIntersection + ", urgencyLevel=" + urgencyLevel + ", waitingTime=" + waitingTime + ", direction=" + Arrays.toString(direction) + '}';
    }
    
    private void setDirectionMovement() {
        int[] originCoordinates = this.utilities.convertCoordinate(this.intersectionOrigin);
        int[] destinationCoordinates = this.utilities.convertCoordinate(this.destinationIntersection);
        this.direction[0] = destinationCoordinates[0] - originCoordinates[0];
        this.direction[1] = destinationCoordinates[1] - originCoordinates[1];
    }
    
    private void getFullPriority() {
        this.totalPriority[0] = this.vehicleType.getPriority();
        this.totalPriority[1] = this.urgencyLevel;
        this.totalPriority[2] = this.waitingTime;
        this.totalPriority[3] = this.direction[0] + this.direction[1];
    }
    
    public void updateCurrentCoordinate() {
        if (!isAtDestination) {
            if (this.direction[0] != 0) {
                if (this.direction[0] < 0) {
                    this.direction[0]++;
                    this.currentCoordinate[0]--;
                } else if (this.direction[0] > 0) {
                    this.direction[0]--;
                    this.currentCoordinate[0]++;
                }
            } else if (this.direction[1] != 0) {
                if (this.direction[1] < 0) {
                    this.direction[1]++;
                    this.currentCoordinate[1]--;
                } else if (this.direction[1] > 0) {
                    this.direction[1]--;
                    this.currentCoordinate[1]++;
                }
            }
        }
    }
    
}
