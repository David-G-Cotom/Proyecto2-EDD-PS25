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
public class LinkedList<T> {
    
    private NodeList<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public NodeList<T> getHead() {
        return head;
    }

    public void setHead(NodeList<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public NodeList<T> getElementAt(int index) {
        if (index < 0 || index >= this.size) return null;
        
        if (index == 0) return this.head;
        
        int counter = 0;
        NodeList<T> current = this.head;
        while (counter < index) {
            current = current.getNext();
            counter++;
        }
        return current;
    }
    
    public NodeList<T> getElementAt(String key) {
        NodeList<T> current = this.head;
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    public void addElementAt(T element) {
        NodeList<T> newNode = new NodeList<>(element);
        if (this.isEmpty()) {
            this.head = newNode;
            ++this.size;
            return;
        }
        NodeList<T> current = this.head;
        while (current.getNext() != null) current = current.getNext();

        current.setNext(newNode);
        ++this.size;
    }

    public T removeElementAt(int index) {
        NodeList<T> current = this.head;
        NodeList<T> prev = null;
        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.getNext();
        }
        if (prev != null) {
            prev.setNext(current.getNext());
        } else {
            this.head = current.getNext();
        }
        current.setNext(null);
        --this.size;
        return current.getData();
    }

    public T removeElements(T element) {
        NodeList<T> current = this.head;
        NodeList<T> prev = null;
        while (current != null && current.getData() != element) {
            prev = current;
            current = current.getNext();
        }
        if (current == null) return null;

        if (prev != null) {
            prev.setNext(current.getNext());
        } else {
            this.head = current.getNext();
        }
        current.setNext(null);
        --this.size;
        return current.getData();
    }

    public boolean isEmpty() {
        return this.size == 0 && this.head == null;
    }
    
}
