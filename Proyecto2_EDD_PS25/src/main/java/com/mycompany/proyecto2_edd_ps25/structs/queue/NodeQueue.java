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
    private NodeQueue<T>  next;

    public NodeQueue(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeQueue<T> getNext() {
        return next;
    }

    public void setNext(NodeQueue<T> next) {
        this.next = next;
    }
    
}
