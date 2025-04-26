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
public class NodeMatrix<T> {
    
    private T data;
    private int x, y;
    private NodeMatrix next;
    private NodeMatrix prev;
    private NodeMatrix top;
    private NodeMatrix bottom;

    public NodeMatrix(T data, int x, int y) {
        this.data = data;
        this.x = x;
        this.y = y;
        this.next = null;
        this.prev = null;
        this.top = null;
        this.bottom = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public NodeMatrix getNext() {
        return next;
    }

    public void setNext(NodeMatrix next) {
        this.next = next;
    }

    public NodeMatrix getPrev() {
        return prev;
    }

    public void setPrev(NodeMatrix prev) {
        this.prev = prev;
    }

    public NodeMatrix getTop() {
        return top;
    }

    public void setTop(NodeMatrix top) {
        this.top = top;
    }

    public NodeMatrix getBottom() {
        return bottom;
    }

    public void setBottom(NodeMatrix bottom) {
        this.bottom = bottom;
    }
    
}
