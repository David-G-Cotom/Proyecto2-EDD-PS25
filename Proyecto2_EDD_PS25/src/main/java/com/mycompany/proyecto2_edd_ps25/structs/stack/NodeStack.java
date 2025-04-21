/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.stack;

/**
 *
 * @author Carlos Cotom
 * @param <T>
 */
public class NodeStack<T> {
    
    private T data;
    private NodeStack<T> next;

    public NodeStack(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeStack<T> getNext() {
        return next;
    }

    public void setNext(NodeStack<T> next) {
        this.next = next;
    }
    
}
