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
public class Stack<T> {

    private NodeStack<T> root;

    public Stack() {
        this.root = null;
    }

    public NodeStack<T> getRoot() {
        return root;
    }

    public void setRoot(NodeStack<T> root) {
        this.root = root;
    }

    public void push(T data) {
        NodeStack<T> newNode = new NodeStack<>(data);
        newNode.setNext(this.root);
        this.root = newNode;
    }

    public T pop() {
        if (this.root == null) return null;

        T data = this.root.getData();
        this.root = this.root.getNext();
        return data;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

}
