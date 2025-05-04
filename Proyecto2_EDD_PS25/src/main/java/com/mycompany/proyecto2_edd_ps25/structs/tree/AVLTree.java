/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.tree;

import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;

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
        this.root = this.insert(newNode, this.root);
    }
    
    private TreeNode insert(TreeNode node, TreeNode root) {
        if (root == null) {
            node.setBalanceFactor(this.obtainBalanceFactor(node));
            //root = node;
            return node;
        }
        if (node.getSize() < root.getSize()) {
            root.setLeft(this.insert(node, root.getLeft()));
        } else {
            root.setRight(this.insert(node, root.getRight()));
        }
        return this.balanceTree(root);
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
    
    private TreeNode balanceTree(TreeNode root) {
        root.setBalanceFactor(this.obtainBalanceFactor(root));
        if (root.getBalanceFactor() < -1) {
            if (root.getLeft().getBalanceFactor() > 0) {
                return this.doubleRightRotation(root);
            }
            return this.rightRotation(root);
        }
        if (root.getBalanceFactor() > 1) {
            if (root.getRight().getBalanceFactor() < 0) {
                return this.doubleLeftRotation(root);
            }
            return this.leftRotation(root);
        }
        return root;
    }
    
    private TreeNode doubleRightRotation(TreeNode node) {
        node.setLeft(this.leftRotation(node.getLeft()));
        return this.rightRotation(node);
    }
    
    private TreeNode rightRotation(TreeNode node) {
        TreeNode aux = node.getLeft();
        node.setLeft(aux.getRight());
        aux.setRight(node);
        //node = aux;
        node.setBalanceFactor(this.obtainBalanceFactor(node));
        aux.setBalanceFactor(this.obtainBalanceFactor(aux));
        return aux;
        //node.getRight().setBalanceFactor(this.obtainBalanceFactor(node.getRight()));
        //if (node.getLeft() == null) return null;
        
        //node.getLeft().setBalanceFactor(this.obtainBalanceFactor(node.getLeft()));
    }
    
    private TreeNode doubleLeftRotation(TreeNode node) {
        node.setRight(this.rightRotation(node.getRight()));
        return this.leftRotation(node);
    }
    
    private TreeNode leftRotation(TreeNode node) {
        TreeNode aux = node.getRight();
        node.setRight(aux.getLeft());
        aux.setLeft(node);
        //node = aux;
        node.setBalanceFactor(this.obtainBalanceFactor(node));
        aux.setBalanceFactor(this.obtainBalanceFactor(aux));
        return aux;
        //node.getLeft().setBalanceFactor(this.obtainBalanceFactor(node.getLeft()));
        //if (node.getRight() == null) return null;
        
        //node.getRight().setBalanceFactor(this.obtainBalanceFactor(node.getRight()));
    }
    
    public void delete(int size, String id) {
        this.root = this.delete(size, this.root, id);
    }
    
    private TreeNode delete(int size, TreeNode root, String id) {
        if (root == null) {
            //System.out.println("Nodo con valor: " + size + " e ID: " + id + " no se encontro para eliminar");
            return null;
        }
        if (size < root.getSize()) {
            root.setLeft(this.delete(size, root.getLeft(), id));
        } else if (size > root.getSize()) {
            root.setRight(this.delete(size, root.getRight(), id));
        } else {
            if (id.equals(root.getId())) {
                if (this.isLeaf(root)) return null;
                if (root.getLeft() == null) return root.getRight();
                if (root.getRight() == null) return root.getLeft();
                
                TreeNode rightNode = this.getMajorNode(root.getLeft());
                root.setData(rightNode.getData());
                root.setSize(rightNode.getSize());
                root.setId(rightNode.getId());
                root.setLeft(this.delete(rightNode.getSize(), root.getLeft(), rightNode.getId()));
            } else {
                //System.out.println("Nodo con valor: " + size + "coincide, pero ID: " + id + " no. Buscando/Eliminando derecha...");
                root.setRight(this.delete(size, root.getRight(), id));
                //System.out.println("Nodo con valor: " + size + "coincide, pero ID: " + id + " no. Buscando/Eliminando izquierda...");
                root.setLeft(this.delete(size, root.getLeft(), id));
            }
            //size = root.getSize();
        }
        return this.balanceTree(root);
    }
    
    public boolean isLeaf(TreeNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }
    
    public void updateNode(int size, int newSize, String id) {
        LinkedList<TreeNode> nodesFound = this.search(size);
        TreeNode node = null;
        TreeNode aux;
        for (int i = 0; i < nodesFound.getSize(); i++) {
            aux = nodesFound.getElementAt(i).getData();
            if (aux.getId().equals(id)) {
                node = aux;
                break;
            }
        }
        if (node != null) {
            TreeNode updatedNode = new TreeNode(node.getData());
            updatedNode.setSize(newSize);
            updatedNode.setId(id);
            this.delete(size, id);
            this.insert(updatedNode);
        }
    }
    
    public TreeNode getMajorNode(TreeNode node) {
        if (node.getRight() == null) {
            return node;
        }
        return getMajorNode(node.getRight());
    }
    
    public LinkedList<TreeNode> search(int size) {
        LinkedList<TreeNode> nodesFound = new LinkedList<>();
        this.search(size, root, nodesFound);
        return nodesFound;
    }
    
    private void search(int size, TreeNode node, LinkedList<TreeNode> list) {
        if (node != null) {
            if (size < node.getSize()) {
                this.search(size, node.getLeft(), list);
            } else if (size > node.getSize()) {
                this.search(size, node.getRight(), list);
            } else {
                list.addElementAt(node, "");
                this.search(size, node.getLeft(), list);
                this.search(size, node.getRight(), list);
            }
        }
    }
    
    public void print(int indentIncrement) {
        System.out.println("\n-------------------- Arbol AVL Visual --------------------");
        if (this.root == null) {
            System.out.println("(Árbol vacío)");
        } else {
            print(this.root, 0, indentIncrement);
        }
        System.out.println("\n");
    }
    
    private void print(TreeNode node, int space, int level) {
        if (node == null) return;

        int newSpace = space + level;
        print(node.getRight(), newSpace, level);
        System.out.println();
        for (int i = 0; i < space; i++) System.out.print(" ");
         
        System.out.print("" + node.getData() + node.getBalanceFactor());
        print(node.getLeft(), newSpace, level);
    }
    
}
