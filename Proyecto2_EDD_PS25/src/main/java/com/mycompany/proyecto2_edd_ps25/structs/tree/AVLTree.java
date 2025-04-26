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
public class AVLTree<T> {
    
    private TreeNode<T> root;

    public AVLTree() {
        this.root = null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }
    
    public void insert(TreeNode newNode) {
        this.insert(newNode, this.root);
    }
    
    private void insert(TreeNode node, TreeNode root) {
        if (root == null) {
            root = node;
            root.setBalanceFactor(this.obtainBalanceFactor(root));
            return;
        }
        if (node.getSize() < root.getSize()) {
            this.insert(node, root.getLeft());
        } else {
            this.insert(node, root.getRight());
        }
        this.balanceTree(root);
    }
    
    private int obtainBalanceFactor(TreeNode node) {
        int leftHeight =  this.getMaximumHeight(node.getLeft());
        int rightHeight =  this.getMaximumHeight(node.getRight());
        return rightHeight - leftHeight;
    }
    
    private int getMaximumHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight =  this.getMaximumHeight(node.getLeft());
        int rightHeight =  this.getMaximumHeight(node.getRight());
        return 1 + (leftHeight > rightHeight ? leftHeight: rightHeight);
    }
    
    private void balanceTree(TreeNode root) {
        root.setBalanceFactor(this.obtainBalanceFactor(root));
        if (root.getBalanceFactor() < -1) {
            if (root.getLeft().getBalanceFactor() > 0) {
                this.doubleRightRotation(root);
                return;
            }
            this.rightRotation(root);
            return;
        }
        if (root.getBalanceFactor() > 1) {
            if (root.getRight().getBalanceFactor() < 0) {
                this.doubleLeftRotation(root);
                return;
            }
            this.leftRotation(root);
        }
    }
    
    private void doubleRightRotation(TreeNode node) {
        this.leftRotation(node.getLeft());
        this.rightRotation(node);
    }
    
    private void rightRotation(TreeNode node) {
        TreeNode aux = node.getLeft();
        node.setLeft(aux.getRight());
        aux.setRight(node);
        node = aux;
        node.setBalanceFactor(this.obtainBalanceFactor(node));
        node.getRight().setBalanceFactor(this.obtainBalanceFactor(node.getRight()));
        if (node.getLeft() == null) return;
        
        node.getLeft().setBalanceFactor(this.obtainBalanceFactor(node.getLeft()));
    }
    
    private void doubleLeftRotation(TreeNode node) {
        this.rightRotation(node.getRight());
        this.leftRotation(node);
    }
    
    private void leftRotation(TreeNode node) {
        TreeNode aux = node.getRight();
        node.setRight(aux.getLeft());
        aux.setLeft(node);
        node = aux;
        node.setBalanceFactor(this.obtainBalanceFactor(node));
        node.getLeft().setBalanceFactor(this.obtainBalanceFactor(node.getLeft()));
        if (node.getRight() == null) return;
        
        node.getRight().setBalanceFactor(this.obtainBalanceFactor(node.getRight()));
    }
    
    public void delete(int size) {
        this.delete(size, this.root);
    }
    
    private void delete(int size, TreeNode root) {
        if (root == null) return;
        
        if (size == root.getSize()) {
            if (this.isLeaf(root)) {
                root = null;
                return;
            }
            if (root.getLeft() == null) {
                root = root.getRight();
                return;
            }
            if (root.getRight() == null) {
                root = root.getLeft();
                return;
            }
            TreeNode rightNode = this.getMajorNode(root.getLeft());
            root.setData(rightNode.getData());
            root.setSize(rightNode.getSize());
            this.delete(rightNode.getSize(), root.getLeft());
            size = root.getSize();
        }
        if (size < root.getSize()) {
            this.delete(size, root.getLeft());
        }
        if (size > root.getSize()) {
            this.delete(size, root.getRight());
        }
        this.balanceTree(root);
    }
    
    public boolean isLeaf(TreeNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }
    
    public void updateNode(int size, int newSize) {
        TreeNode node = this.search(size);
        if (node != null) {
            TreeNode updatedNode = new TreeNode(node.getData());
            updatedNode.setSize(newSize);
            this.delete(size);
            this.insert(updatedNode);
        }
    }
    
    public TreeNode getMajorNode(TreeNode node) {
        if (node.getRight() == null) {
            return node;
        }
        return getMajorNode(node.getRight());
    }
    
    public TreeNode search(int size) {
        return this.search(size, this.root);
    }
    
    private TreeNode search(int size, TreeNode node) {
        if (node != null) {
            if (size == node.getSize()) return node;

            if (size < node.getSize()) {
                return this.search(size, node.getLeft());
            }
            if (size > node.getSize()) {
                return this.search(size, node.getRight());
            }
        }
        return null;
    }
    
    public void print(TreeNode root, int space, int level) {
        if (root == null) return;
        
        space += level;
        this.print(root.getRight(), space, level);
        System.out.println("");
        for (int i = level; i < space; i++) {
            System.out.println(" ");
        }
        System.out.println(root.getData() + "(" + root.getData() + ")");
        this.print(root.getLeft(), space, level);
    }
    
}
