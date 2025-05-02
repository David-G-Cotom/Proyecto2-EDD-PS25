/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.models;

import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.NodeMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.OrthogonalMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.StreetType;

/**
 *
 * @author Carlos Cotom
 */
public class City {
    
    private OrthogonalMatrix<Intersection> matrix;
    private int incrasedCongestion;

    public City(int dimensionX, int dimensionY) {
        this.matrix = new OrthogonalMatrix<>(dimensionX, dimensionY);
        this.incrasedCongestion = this.matrix.getIncrasedCongestion();
        this.updateCityTemplate();
    }

    public OrthogonalMatrix<Intersection> getMatrix() {
        return matrix;
    }

    public void setMatrix(OrthogonalMatrix<Intersection> matrix) {
        this.matrix = matrix;
    }

    public int getIncrasedCongestion() {
        return incrasedCongestion;
    }

    public void setIncrasedCongestion(int incrasedCongestion) {
        this.incrasedCongestion = incrasedCongestion;
    }
    
    public boolean checkDimensions(int rowDimensions, int columnDimensions, LinkedList<Intersection> newIntersections) {
        int incrementX = rowDimensions - this.matrix.getDimensionX();
        int incrementY = columnDimensions - this.matrix.getDimensionY();
        if (incrementX > 0 || incrementY > 0) {
            this.matrix.incraseMatrix(incrementX, incrementY, newIntersections);
            if (this.incrasedCongestion < this.matrix.getIncrasedCongestion()) this.incrasedCongestion = this.matrix.getIncrasedCongestion();
            this.updateCityTemplate();
            return true;
        }
        return false;
    }
    
    public NodeMatrix<Intersection> putVehicle(Vehicle newVehicle, int[] origin) {
        NodeMatrix<Intersection> node = this.matrix.getNode(origin[0] - 1, origin[1] - 1);
        node.getData().incraseComplexity(newVehicle);
        return node;
    }
    
    public void printCity() {
        int height = StreetType.INTERSECCION.getLines().length;
        int maxWidth = 0;
        for (int i = 0; i < this.matrix.getDimensionX(); i++) {
            for (int j = 0; j < this.matrix.getDimensionY(); j++) {
                StreetType t = this.matrix.getNode(i, j).getStreetType();
                for (String line : t.getLines()) {
                    maxWidth = Math.max(maxWidth, line.length());
                }
            }
        }
        int labelWidth = 2;
        System.out.print(" ".repeat(labelWidth));
        for (int i = 0; i < this.matrix.getDimensionY(); i++) {
            System.out.print(String.format("%-" + maxWidth + "s", i + 1));  
        }
        System.out.println();
        for (int i = 0; i < this.matrix.getDimensionX(); i++) {
            char rowChar = (char)('A' + i);
            for (int line = 0; line < height; line++) {
                if (line == 1) {
                    System.out.print(String.format("%-" + labelWidth + "s", rowChar));
                } else {
                    System.out.print(" ".repeat(labelWidth));
                }
                for (int j = 0; j < this.matrix.getDimensionY(); j++) {
                    StreetType type = this.matrix.getNode(i, j).getStreetType();
                    String[] lines = type.getLines();
                    String fragment = (line < lines.length ? lines[line] : "");
                    System.out.print(String.format("%-" + maxWidth + "s", fragment));
                }
                System.out.println();
            }
        }
        System.out.println("");
    }
    
    private void updateCityTemplate() {
        NodeMatrix<Intersection> aux = this.matrix.getRoot();
        for (int i = 0; i < this.matrix.getDimensionY(); i++) {
            for (int j = 0; j < this.matrix.getDimensionX(); j++) {
                this.setStreetType(aux);
                aux = aux.getNext();
            }
            aux = this.matrix.getNode(i, 0);
            aux = aux.getBottom();
        }
    }
    
    private void setStreetType(NodeMatrix<Intersection> node) {
        if (node.getX() == 0 && node.getY() == 0) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_ESQUINA_1_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_ESQUINA_1_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_ESQUINA_1);
            }
        } else if (node.getX() == 0 && node.getNext() != null) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_NORTE_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_NORTE_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_NORTE);
            }
        } else if (node.getX() == 0 && node.getNext() == null) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_ESQUINA_2_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_ESQUINA_2_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_ESQUINA_2);
            }
        } else if (node.getY() == 0 && node.getBottom() != null) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_IZQUIERDA_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_IZQUIERDA_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_IZQUIERDA);
            }
        } else if (node.getY() == 0 && node.getBottom() == null) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_ESQUINA_3_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_ESQUINA_3_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_ESQUINA_3);
            }
        } else if (node.getX() == this.matrix.getDimensionX() - 1 && node.getNext() != null) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_SUR_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_SUR_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_SUR);
            }
        } else if (node.getY() == this.matrix.getDimensionY() - 1 && node.getBottom() != null) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_DERECHA_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_DERECHA_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_DERECHA);
            }
        } else if (node.getX() == this.matrix.getDimensionX() - 1 && node.getY() == this.matrix.getDimensionY() - 1) {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.CRUCE_ESQUINA_4_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.CRUCE_ESQUINA_4_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.CRUCE_ESQUINA_4);
            }
        } else {
            switch (node.getData().getIntersectionType()) {
                case SEMAFORO_ROJO, SEMAFORO_VERDE -> node.setStreetType(StreetType.INTERSECCION_SEMAFORO);
                case BLOQUEO, CRUCE_CERRADO, VEHICULO_VARADO -> node.setStreetType(StreetType.INTERSECCION_NO_LIBRE);
                case LIBRE, CRUCE_LIBRE, VEHICULOS_TRANSITANDO -> node.setStreetType(StreetType.INTERSECCION);
            }
        }
    }
    
}
