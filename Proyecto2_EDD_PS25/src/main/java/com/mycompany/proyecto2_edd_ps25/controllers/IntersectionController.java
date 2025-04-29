/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.controllers;

import com.mycompany.proyecto2_edd_ps25.models.City;
import com.mycompany.proyecto2_edd_ps25.models.Intersection;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.NodeMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.tree.AVLTree;
import com.mycompany.proyecto2_edd_ps25.structs.tree.TreeNode;

/**
 *
 * @author Carlos Cotom
 */
public class IntersectionController {
    
    private City city;
    private AVLTree avlTree;

    public IntersectionController(City city, AVLTree avlTree) {
        this.city = city;
        this.avlTree = avlTree;
        this.buildTree();
    }
    
    private void buildTree() {
        NodeMatrix<Intersection> aux = this.city.getMatrix().getRoot();
        for (int i = 0; i < this.city.getMatrix().getDimensionY(); i++) {
            for (int j = 0; j < this.city.getMatrix().getDimensionX(); j++) {
                TreeNode<Intersection> newNode = new TreeNode<>(aux.getData(), aux.getData().getComplexity(), aux.getData().getId());
                this.avlTree.insert(newNode);
                aux = aux.getNext();
            }
            aux = this.city.getMatrix().getNode(i, 0);
            aux = aux.getBottom();
        }
    }
    
    public void updateTree(LinkedList<Intersection> newIntersections) {
        for (int i = 0; i < newIntersections.getSize(); i++) {
            Intersection intersection = newIntersections.getElementAt(i).getData();
            TreeNode<Intersection> newNode = new TreeNode<>(intersection, intersection.getComplexity(), intersection.getId());
            this.avlTree.insert(newNode);
        }
    }
    
}
