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
    
}
