/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

/**
 *
 * @author Carlos Cotom
 */
public enum IntersectionComplexityType {
    
    BLOQUEO(4),
    LIBRE(0),
    SEMAFORO_ROJO(3),
    SEMAFORO_VERDE(0),
    CRUCE_CERRADO(2),
    CRUCE_LIBRE(0),
    VEHICULO_VARADO(1),
    VEHICULOS_TRANSITANDO(0);
    
    private int priority;

    private IntersectionComplexityType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
}
