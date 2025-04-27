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
public class PriorityQueue<T> {

    private NodeQueue<T> head;

    public PriorityQueue() {
        this.head = null;
    }

    public NodeQueue<T> getHead() {
        return head;
    }

    public void setHead(NodeQueue<T> head) {
        this.head = head;
    }

    public void enqueue(T data, int priority) {
        NodeQueue<T> newNode = new NodeQueue<>(data, priority);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        NodeQueue<T> current = this.head;
        NodeQueue<T> prev = null;
        while (current != null) {
            if (priority > current.getPriority()) {
                if (prev == null) { // Insertar al inicio
                    newNode.setNext(this.head);
                    this.head = newNode;
                } else { // Insertar entre anterior y actual
                    prev.setNext(newNode);
                    newNode.setNext(current);
                }
                return;
            } else {
                prev = current;
                current = current.getNext();
            }
        }
        // Si no se insertó, agregar al final
        if (prev != null) prev.setNext(newNode);
    }

    public T unqueue() {
        if (this.head == null) return null;

        T data = this.head.getData();
        this.head = this.head.getNext();
        return data;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

}
