/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.matrix;

import com.mycompany.proyecto2_edd_ps25.models.Intersection;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import com.mycompany.proyecto2_edd_ps25.utils.Utilities;

/**
 *
 * @author Carlos Cotom
 * @param <T>
 */
public class OrthogonalMatrix<T> {
    
    private NodeMatrix root; //Nodo en la posicion (0,0)
    int dimensionX, dimensionY;
    private final Utilities utilities;
    private int incrasedCongestion;

    public OrthogonalMatrix(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        Intersection newIntersection = new Intersection("A1");
        this.incrasedCongestion = newIntersection.getComplexity();
        this.root = new NodeMatrix<>(newIntersection, 0, 0);
        this.utilities = new Utilities();
        this.createOrthogonalMatrix();
    }

    public NodeMatrix<T> getRoot() {
        return root;
    }

    public void setRoot(NodeMatrix<T> root) {
        this.root = root;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }

    public int getIncrasedCongestion() {
        return incrasedCongestion;
    }

    public void setIncrasedCongestion(int incrasedCongestion) {
        this.incrasedCongestion = incrasedCongestion;
    }
    
    private void createOrthogonalMatrix() {
        NodeMatrix<T> aux = this.root;
        for (int i = 0; i < this.dimensionX - 1; i++) {
            this.connectNodes(aux, i);
            aux = this.getNode(i, 0);
            String coordinateLetter = this.utilities.convertCoordinate(i + 1);
            String coordinateNumber = "1";
            Intersection newIntersection = new Intersection(coordinateLetter + coordinateNumber);
            if (this.incrasedCongestion < newIntersection.getComplexity()) this.incrasedCongestion = newIntersection.getComplexity();
            NodeMatrix newNode = new NodeMatrix<>(newIntersection, i + 1, 0);
            aux.setBottom(newNode);
            newNode.setTop(aux);
            aux = newNode;
            this.connectNodes(aux, i + 1);
        }
    }

    private void connectNodes(NodeMatrix<T> aux, int i) {
        for (int j = 0; j < this.dimensionY - 1; j++) {
            String coordinateLetter = this.utilities.convertCoordinate(i);
            String coordinateNumber = String.valueOf(j + 2);
            Intersection newIntersection = new Intersection(coordinateLetter + coordinateNumber);
            if (this.incrasedCongestion < newIntersection.getComplexity()) this.incrasedCongestion = newIntersection.getComplexity();
            NodeMatrix newNode = new NodeMatrix<>(newIntersection, i, j + 1);
            aux.setNext(newNode);
            newNode.setPrev(aux);
            if (i > 0) {
                newNode.setTop(aux.getTop().getNext());
                aux.getTop().getNext().setBottom(newNode);
            }
            aux = newNode;
        }
    }
    
    public boolean isEmpty() {
        return this.root == null;
    }

    public NodeMatrix<T> getNode(int x, int y) {
        NodeMatrix<T> aux = this.root;
        for (int i = 0; i < x; ++i) aux = aux.getBottom();

        for (int i = 0; i < y; ++i) aux = aux.getNext();

        return aux;
    }
    
    public void incraseMatrix(int incrementX, int incrementY, LinkedList<Intersection> newIntersections) {
        NodeMatrix<T> aux = this.getNode(0, this.dimensionY - 1);
        for (int i = 0; i < this.dimensionX - 1; i++) {
            this.incrementColumns(aux, i, incrementY, newIntersections);
            aux = this.getNode(i + 1, this.dimensionY - 1);
            this.incrementColumns(aux, i + 1, incrementY, newIntersections);
        }
        if (incrementX > 0) {
            for (int i = this.dimensionX; i < this.dimensionX + incrementX; i++) {
                aux = this.getNode(i - 1, 0);
                String coordinateLetter = this.utilities.convertCoordinate(i);
                String coordinateNumber = "1";
                Intersection newIntersection = new Intersection(coordinateLetter + coordinateNumber);
                if (this.incrasedCongestion < newIntersection.getComplexity()) this.incrasedCongestion = newIntersection.getComplexity();
                newIntersections.addElementAt(newIntersection);
                NodeMatrix newNode = new NodeMatrix(newIntersection, i, 0);
                newNode.setTop(aux);
                aux.setBottom(newNode);
                aux = newNode;
                this.incrementRows(aux, i, incrementY, newIntersections);
            }
            this.dimensionX += incrementX;
        }
        if (incrementY > 0) this.dimensionY += incrementY;
    }
    
    private void incrementColumns(NodeMatrix<T> aux, int i, int incrementY, LinkedList<Intersection> newIntersections) {
        for (int j = 0; j < incrementY; j++) {
            String coordinateLetter = this.utilities.convertCoordinate(i);
            String coordinateNumber = String.valueOf(aux.getY() + 2);
            Intersection newIntersection = new Intersection(coordinateLetter + coordinateNumber);
            if (this.incrasedCongestion < newIntersection.getComplexity()) this.incrasedCongestion = newIntersection.getComplexity();
            newIntersections.addElementAt(newIntersection);
            NodeMatrix newNode = new NodeMatrix(newIntersection, i, aux.getY() + 1);
            aux.setNext(newNode);
            newNode.setPrev(aux);
            if (i > 0) {
                newNode.setTop(aux.getTop().getNext());
                aux.getTop().getNext().setBottom(newNode);
            }
            aux = newNode;
        }
    }
    
    private void incrementRows(NodeMatrix<T> aux, int i, int incrementY, LinkedList<Intersection> newIntersections) {
        for (int j = 0; j < this.dimensionY + incrementY - 1; j++) {
            String coordinateLetter = this.utilities.convertCoordinate(i);
            String coordinateNumber = String.valueOf(j + 2);
            Intersection newIntersection = new Intersection(coordinateLetter + coordinateNumber);
            if (this.incrasedCongestion < newIntersection.getComplexity()) this.incrasedCongestion = newIntersection.getComplexity();
            newIntersections.addElementAt(newIntersection);
            NodeMatrix newNode = new NodeMatrix(newIntersection, i, j + 1);
            aux.setNext(newNode);
            newNode.setPrev(aux);
            newNode.setTop(aux.getTop().getNext());
            aux.getTop().getNext().setBottom(newNode);
            aux = newNode;
        }
    }
    
}
