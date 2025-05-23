/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

/**
 *
 * @author Carlos Cotom
 */
public enum VehicleType {
    
    AMBULANCIA(4),
    POLICIA(3),
    TRANSPORTE(2),
    PARTICULAR(1);
    
    private int priority;

    private VehicleType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
}
