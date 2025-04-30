/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.controllers;

import com.mycompany.proyecto2_edd_ps25.models.City;
import com.mycompany.proyecto2_edd_ps25.models.Intersection;
import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import com.mycompany.proyecto2_edd_ps25.structs.hash.HashTable;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.NodeMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.tree.AVLTree;
import com.mycompany.proyecto2_edd_ps25.utils.Utilities;
import com.mycompany.proyecto2_edd_ps25.utils.Posters;

/**
 *
 * @author Carlos Cotom
 */
public class SystemController {
    
    private final Posters posters;
    private final Utilities utilities;
    private LinkedList<Vehicle> vehicles;
    private LinkedList<int[]> coordinates;
    private City city;
    private HashTable<Vehicle> hashTable;
    private AVLTree<Intersection> avlTree;
    private IntersectionController intersectionsController;
    private LinkedList<Intersection> newsIntersections;

    public SystemController() {
        this.posters = new Posters();
        this.utilities = new Utilities();
        this.vehicles = new LinkedList<>();
        this.coordinates = new LinkedList<>();
        this.city = new City(10, 10); //Dimensiones iniciales de la ciudad
        this.hashTable = new HashTable<>();
        this.avlTree = new AVLTree<>();
        this.intersectionsController = new IntersectionController(this.city, this.avlTree);
        this.newsIntersections = new LinkedList<>();
        this.avlTree.print(5);
    }
    
    private void enterVechicle() {
        VehicleType vehicleType = this.posters.incomeType();
        String plate = this.posters.entrancePlate();
        String origin = this.posters.incomeSource();
        String destination = this.posters.destinationIncome();
        int priority = this.posters.priorityEntry();
        int waitingTime = this.posters.incomeWaitingTime();
        Vehicle newVechicle = new Vehicle(vehicleType, plate, origin, destination, waitingTime, priority);
        this.hashTable.insert(plate, newVechicle);
        int[] originCoordinates = this.utilities.convertCoordinate(origin);
        int[] destinationCoordinates = this.utilities.convertCoordinate(destination);
        int dimensionX = originCoordinates[0] > destinationCoordinates[0] ? originCoordinates[0] : destinationCoordinates[0];
        int dimensionY = originCoordinates[1] > destinationCoordinates[1] ? originCoordinates[1] : destinationCoordinates[1];
        if (this.city.checkDimensions(dimensionX, dimensionY, this.newsIntersections)) {
            this.intersectionsController.updateTree(this.newsIntersections);
            this.newsIntersections.clearList();
        }
        NodeMatrix<Intersection> node = this.city.putVehicle(newVechicle, originCoordinates);
        this.avlTree.updateNode(node.getData().getComplexity() - 1, node.getData().getComplexity(), node.getData().getId());
        System.out.println("Vehiculo Registrado en el Sistema");
    }
    
    public void processVehicle() {
        
    }
    
    public void execute() {
        int option = this.posters.mainMenu();
        switch (option) {
            case 1 -> this.start();
            case 2 -> System.out.println("Fin de la Simulacion");
        }
    }
    
    private void start() {
        int option = this.posters.menuOrderVehicles();
        switch (option) {
            case 1 -> this.enterVechicle();
            case 2 -> {
                this.utilities.readTrafficFile("trafico.csv", this.vehicles, this.coordinates);
                int dimensionX = 0, dimensionY = 0;
                for (int i = 0; i < this.coordinates.getSize(); i++) {
                    int[] coordinate = this.coordinates.getElementAt(i).getData();
                    int currentX = coordinate[0];
                    if (currentX > dimensionX) dimensionX = currentX;
                    
                    int currentY = coordinate[1];
                    if (currentY > dimensionY) dimensionY = currentY;
                }
                if (this.city.checkDimensions(dimensionX, dimensionY, this.newsIntersections)) {
                    this.intersectionsController.updateTree(this.newsIntersections);
                    this.newsIntersections.clearList();
                }
                for (int i = 0; i < this.coordinates.getSize(); i+=2) {
                    Vehicle vehicle = this.vehicles.getElementAt(i/2).getData();
                    NodeMatrix<Intersection> node = this.city.putVehicle(vehicle, this.coordinates.getElementAt(i).getData());
                    this.avlTree.updateNode(node.getData().getComplexity() - 1, node.getData().getComplexity(), node.getData().getId());
                    this.hashTable.insert(vehicle.getPlate(), vehicle);
                }
            }
        }
        this.city.printCity();
        this.avlTree.print(5);
    }
    
    public void registerEvent() {
        
    }
    
    public void generateReports() {
        
    }
    
}
