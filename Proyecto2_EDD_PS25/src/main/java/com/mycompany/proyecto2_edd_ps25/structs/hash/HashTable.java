/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.structs.hash;

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
            hash += c - '-' + 1;
        }
        return hash % this.map.length;
    }
    
    public T get(String key) {
        int hash = this.hashFunction(key);
        return this.map[hash].getElementAt(key).getData();
    }
    
}
