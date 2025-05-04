/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.utils;

import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import com.mycompany.proyecto2_edd_ps25.structs.list.NodeList;

/**
 *
 * @author Carlos Cotom
 */
public class SortingAlgorithms {

    public SortingAlgorithms() {
    }
    
    public void insertionSort(LinkedList<Vehicle> list) {
        NodeList<Vehicle> sortedHead = null;
        NodeList<Vehicle> current = list.getHead();
        while (current != null) {
            NodeList<Vehicle> nextNode = current.getNext();
            if (sortedHead == null
                    || compare(current.getData(), sortedHead.getData()) <= 0) {
                //Insertar al frente
                current.setNext(sortedHead);
                sortedHead = current;
            } else {
                //Encontrar posicion
                NodeList<Vehicle> aux = sortedHead;
                while (aux.getNext() != null
                        && compare(aux.getNext().getData(), current.getData()) < 0) {
                    aux = aux.getNext();
                }
                //Insertar entre aux y aux.next
                current.setNext(aux.getNext());
                aux.setNext(current);
            }
            current = nextNode;
        }
        //Acutalizar cabeza de la lista original
        list.setHead(sortedHead);
    }
    
    private int compare(Vehicle currentVehicle, Vehicle nextVehicle) {
        int result = Integer.compare(nextVehicle.getUrgencyLevel(), currentVehicle.getUrgencyLevel());
        if (result != 0) return result;
        
        return Integer.compare(nextVehicle.getWaitingTime(), currentVehicle.getWaitingTime());
    }
    
    public void quickSort() {
        
    }
    
    public void shaker_cocktail_Sort() {
        
    }
    
}
