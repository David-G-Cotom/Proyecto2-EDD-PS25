/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.tree;

/**
 *
 * @author Carlos Cotom
 * @param <T>
 */
public class TreeNode<T> {
    
    private T data;
    private int balanceFactor;
    private int size;
    private String id;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(T data, int size, String id) {
        this.data = data;
        this.balanceFactor = 0;
        this.size = size;
        this.id = id;
        this.left = null;
        this.right = null;
    }

    public TreeNode(T data) {
        this.data = data;
        this.balanceFactor = 0;
        this.size = 0;
        this.left = null;
        this.right = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
    
}
