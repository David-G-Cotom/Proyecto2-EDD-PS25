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
public class Queue<T> {

    private NodeQueue<T> start;
    private NodeQueue<T> end;

    public Queue() {
        this.start = null;
        this.end = null;
    }

    public NodeQueue<T> getStart() {
        return start;
    }

    public void setStart(NodeQueue<T> start) {
        this.start = start;
    }

    public NodeQueue<T> getEnd() {
        return end;
    }

    public void setEnd(NodeQueue<T> end) {
        this.end = end;
    }

    public void enqueue(T data) {
        NodeQueue<T> newNode = new NodeQueue<>(data);
        if (this.end == null) {
            this.start = this.end = newNode;
            return;
        }
        this.end.setNext(newNode);
        this.end = newNode;
    }

    public T unqueue() {
        if (this.start == null) return null;

        T data = this.start.getData();
        this.start = this.start.getNext();
        if (this.start == null) this.end = null;

        return data;
    }

    public boolean isEmpty() {
        return this.start == null;
    }

}
