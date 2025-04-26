/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.queue;

/**
 *
 * @author Carlos Cotom
 * @param <T>
 */
public class NodeQueue<T> {
    
    private T data;
    private int priority;
    private NodeQueue<T>  next;

    public NodeQueue(T data) {
        this.data = data;
        this.next = null;
    }

    public NodeQueue(T data, int priority) {
        this.data = data;
        this.priority = priority;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public NodeQueue<T> getNext() {
        return next;
    }

    public void setNext(NodeQueue<T> next) {
        this.next = next;
    }
    
}
