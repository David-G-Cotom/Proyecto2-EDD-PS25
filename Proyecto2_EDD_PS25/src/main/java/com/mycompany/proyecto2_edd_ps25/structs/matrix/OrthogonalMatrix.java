/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.matrix;

/**
 *
 * @author Carlos Cotom
 * @param <T>
 */
public class OrthogonalMatrix<T> {
    
    private NodeMatrix<T> root; //Nodo en la posicion (0,0)
    int dimensionX, dimensionY;

    public OrthogonalMatrix(int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.root = new NodeMatrix<>(null, 0, 0);
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
    
    private void createOrthogonalMatrix() {
        NodeMatrix<T> aux = this.root;
        for (int i = 0; i < this.dimensionX - 1; i++) {
            this.connectNodes(aux, i);
            aux = this.getNode(i, 0);
            NodeMatrix newNode = new NodeMatrix<T>(null, i + 1, 0);
            aux.setBottom(newNode);
            newNode.setTop(aux);
            aux = newNode;
            this.connectNodes(aux, i + 1);
        }
        System.out.println("CIUDAD CREADA CON EXITO!!!");
    }

    private void connectNodes(NodeMatrix<T> aux, int i) {
        for (int j = 0; j < this.dimensionY - 1; j++) {
            NodeMatrix newNode = new NodeMatrix<T>(null, i, j + 1);
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
    
    public void incraseMatrix(int incrementX, int incrementY) {
        NodeMatrix<T> aux = this.getNode(0, this.dimensionY - 1);
        for (int i = 0; i < this.dimensionX - 1; i++) {
            this.incrementColumns(aux, i, incrementY);
            aux = this.getNode(i + 1, this.dimensionY - 1);
            this.incrementColumns(aux, i + 1, incrementY);
        }
        if (incrementX > 0) {
            for (int i = this.dimensionX; i < this.dimensionX + incrementX; i++) {
                aux = this.getNode(this.dimensionX - 1, 0);
                NodeMatrix newNode = new NodeMatrix(null, this.dimensionX, 0);
                newNode.setTop(aux);
                aux.setBottom(newNode);
                aux = newNode;
                this.incrementRows(aux, i, incrementY);
            }
            this.dimensionX += incrementX;
        }
        if (incrementY > 0) this.dimensionY += incrementY;
    }
    
    private void incrementColumns(NodeMatrix<T> aux, int i, int incrementY) {
        for (int j = 0; j < incrementY; j++) {
            NodeMatrix newNode = new NodeMatrix(null, i, aux.getY() + 1);
            aux.setNext(newNode);
            newNode.setPrev(aux);
            if (i > 0) {
                newNode.setTop(aux.getTop().getNext());
                aux.getTop().getNext().setBottom(newNode);
            }
            aux = newNode;
        }
    }
    
    private void incrementRows(NodeMatrix<T> aux, int i, int incrementY) {
        for (int j = 0; j < this.dimensionY + incrementY - 1; j++) {
            NodeMatrix newNode = new NodeMatrix(null, i, j + 1);
            aux.setNext(newNode);
            newNode.setPrev(aux);
            newNode.setTop(aux.getTop().getNext());
            aux.getTop().getNext().setBottom(newNode);
            aux = newNode;
        }
    }
    
    public void updateCityTemplate() {
        NodeMatrix<T> aux = this.root;
        for (int i = 0; i < this.dimensionY; i++) {
            for (int j = 0; j < this.dimensionX; j++) {
                this.setStreetType(aux);
                aux = aux.getNext();
            }
            aux = this.getNode(i, 0);
            aux = aux.getBottom();
        }
    }
    
    private void setStreetType(NodeMatrix node) {
        if (node.getX() == 0 && node.getY() == 0) {
            node.setStreetType(StreetType.CRUCE_ESQUINA_1);
        } else if (node.getX() == 0 && node.getNext() != null) {
            node.setStreetType(StreetType.CRUCE_NORTE);
        } else if (node.getX() == 0 && node.getNext() == null) {
            node.setStreetType(StreetType.CRUCE_ESQUINA_2);
        } else if (node.getY() == 0 && node.getBottom() != null) {
            node.setStreetType(StreetType.CRUCE_IZQUIERDA);
        } else if (node.getY() == 0 && node.getBottom() == null) {
            node.setStreetType(StreetType.CRUCE_ESQUINA_3);
        } else if (node.getX() == this.dimensionX - 1 && node.getNext() != null) {
            node.setStreetType(StreetType.CRUCE_SUR);
        } else if (node.getY() == this.dimensionY - 1 && node.getBottom() != null) {
            node.setStreetType(StreetType.CRUCE_DERECHA);
        } else if (node.getX() == this.dimensionX - 1 && node.getY() == this.dimensionY - 1) {
            node.setStreetType(StreetType.CRUCE_ESQUINA_4);
        } else {
            node.setStreetType(StreetType.INTERSECCION);
        }
    }
    
}
