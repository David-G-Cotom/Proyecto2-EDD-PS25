/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

/**
 *
 * @author Carlos Cotom
 */
public class Event {
    
    private Vehicle vehicleInvolved;
    private int priorityAttended;
    private int passingTime;
    private String description;

    public Event(Vehicle vehicleInvolved, int priorityAttended, int passingTime, String description) {
        this.vehicleInvolved = vehicleInvolved;
        this.priorityAttended = priorityAttended;
        this.passingTime = passingTime;
        this.description = description;
    }

    public Vehicle getVehicleInvolved() {
        return vehicleInvolved;
    }

    public void setVehicleInvolved(Vehicle vehicleInvolved) {
        this.vehicleInvolved = vehicleInvolved;
    }

    public int getPriorityAttended() {
        return priorityAttended;
    }

    public void setPriorityAttended(int priorityAttended) {
        this.priorityAttended = priorityAttended;
    }

    public int getPassingTime() {
        return passingTime;
    }

    public void setPassingTime(int passingTime) {
        this.passingTime = passingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" + "vehicleInvolved=" + vehicleInvolved + ", priorityAttended=" + priorityAttended + ", passingTime=" + passingTime + ", description=" + description + '}';
    }    
    
}
