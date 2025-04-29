/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.hash;

import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;

/**
 *
 * @author Carlos Cotom
 * @param <T>
 */
public class HashTable<T> {
    
    private LinkedList<T>[] map;

    public HashTable() {
        this.map = new LinkedList[20];
        for (int i = 0; i < this.map.length; i++) {
            this.map[i] = new LinkedList<>();
        }
    }

    public LinkedList<T>[] getMap() {
        return map;
    }

    public void setMap(LinkedList<T>[] map) {
        this.map = map;
    }
    
    public void insert(String key, T data) {
        int hash = this.hashFunction(key);
        this.map[hash].addElementAt(data);
    }
    
    private int hashFunction(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            if (Character.isLetter(c)) {
                hash += c - 'A' + 1;
            } else if (Character.isDigit(c)) {
                hash += c - '0' + 1;
            } else {
                hash += c - '-' + 1;
            }
        }
        return hash % this.map.length;
    }
    
    public T get(String key) {
        int hash = this.hashFunction(key);
        return this.map[hash].getElementAt(key).getData();
    }
    
    public LinkedList<Vehicle> getCollisions() {
        LinkedList<Vehicle> list = new LinkedList<>();
        LinkedList<String> singlePlates = new LinkedList<>();
        for (LinkedList<T> linkedList : this.map) {
            for (int i = 0; i < linkedList.getSize(); i++) {
                Vehicle cuurent = (Vehicle) linkedList.getElementAt(i).getData();
                String currentPlate = cuurent.getPlate();
                if (isOnTheList(list, currentPlate)) continue;

                for (int j = i + 1; j < linkedList.getSize(); j++) {
                    Vehicle compare = (Vehicle) linkedList.getElementAt(j).getData();
                    if (currentPlate.equals(compare.getPlate())) {
                        if (!isOnTheList(list, currentPlate)) {
                            list.addElementAt(cuurent);
                            singlePlates.addElementAt(currentPlate);
                        }
                        list.addElementAt(compare);
                    }
                }
            }
        }
        return list;
    }
    
    private boolean isOnTheList(LinkedList<Vehicle> list, String plate) {
        for (int i = 0; i < list.getSize(); i++) {
            Vehicle vehicle = list.getElementAt(i).getData();
            if (vehicle.getPlate().equals(plate)) return true;
        }
        return false;
    }
    
}
