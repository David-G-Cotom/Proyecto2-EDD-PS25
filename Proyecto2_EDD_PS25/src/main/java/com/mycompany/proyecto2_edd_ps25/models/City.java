/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

import com.mycompany.proyecto2_edd_ps25.structs.matrix.OrthogonalMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.StreetType;

/**
 *
 * @author Carlos Cotom
 */
public class City {
    
    private OrthogonalMatrix<String> city;

    public City(int dimensionX, int dimensionY) {
        this.city = new OrthogonalMatrix<>(dimensionX, dimensionY);
        this.city.updateCityTemplate();
    }

    public OrthogonalMatrix<String> getCity() {
        return city;
    }

    public void setCity(OrthogonalMatrix<String> city) {
        this.city = city;
    }
    
    public void checkDimensions(int rowDimensions, int columnDimensions) {
        int incrementX = rowDimensions - this.city.getDimensionX();
        int incrementY = columnDimensions - this.city.getDimensionY();
        if (incrementX > 0 || incrementY > 0) {
            this.city.incraseMatrix(incrementX, incrementY);
            this.city.updateCityTemplate();
        }
    }
    
    public String convertCoordinate(int value) {
        
        return null;
        
    }
    
    public void printCity() {
        int height = StreetType.INTERSECCION.getLines().length;
        int maxWidth = 0;
        for (int i = 0; i < this.city.getDimensionX(); i++) {
            for (int j = 0; j < this.city.getDimensionY(); j++) {
                StreetType t = this.city.getNode(i, j).getStreetType();
                for (String line : t.getLines()) {
                    maxWidth = Math.max(maxWidth, line.length());
                }
            }
        }
        for (int i = 0; i < this.city.getDimensionX(); i++) {
            for (int line = 0; line < height; line++) {
                for (int j = 0; j < this.city.getDimensionY(); j++) {
                    StreetType type = this.city.getNode(i, j).getStreetType();
                    String[] lines = type.getLines();
                    String fragment;
                    if (line < lines.length) {
                        fragment = lines[line];
                    } else {
                        fragment = ""; 
                    }
                    System.out.print(String.format("%-" + maxWidth + "s", fragment));
                }
                System.out.println();
            }
        }
    }
    
}
