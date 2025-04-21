/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

/**
 *
 * @author Carlos Cotom
 */
public class Intersection {
    
    private String id;
    private int complexity;

    public Intersection(String id, int complexity) {
        this.id = id;
        this.complexity = complexity;
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

    @Override
    public String toString() {
        return "Intersection{" + "id=" + id + ", complexity=" + complexity + '}';
    }
    
}
