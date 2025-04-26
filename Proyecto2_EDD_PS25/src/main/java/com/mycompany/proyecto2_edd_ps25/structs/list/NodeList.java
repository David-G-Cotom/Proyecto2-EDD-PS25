/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.list;

/**
 *
 * @author Carlos Cotom
 * @param <T>
 */
public class NodeList<T> {
    
    private T data;
    private String key;
    private NodeList<T> next;

    public NodeList(T data) {
        this.data = data;
        this.next = null;
    }

    public NodeList(T data, String key) {
        this.data = data;
        this.key = key;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public NodeList<T> getNext() {
        return next;
    }

    public void setNext(NodeList<T> next) {
        this.next = next;
    }
    
}
