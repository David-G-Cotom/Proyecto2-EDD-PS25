/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.controllers;

import com.mycompany.proyecto2_edd_ps25.models.City;
import com.mycompany.proyecto2_edd_ps25.models.Event;
import com.mycompany.proyecto2_edd_ps25.models.Intersection;
import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import com.mycompany.proyecto2_edd_ps25.structs.hash.HashTable;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.NodeMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.stack.Stack;
import com.mycompany.proyecto2_edd_ps25.structs.tree.AVLTree;
import com.mycompany.proyecto2_edd_ps25.structs.tree.TreeNode;
import com.mycompany.proyecto2_edd_ps25.utils.Utilities;
import com.mycompany.proyecto2_edd_ps25.utils.Posters;
import java.util.Scanner;

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
    private Stack<Event> recordedEvents;
    private int vehiclesInTheCity;
    private ReportsController reportsController;
    
    private final String FILE_NAME = "trafico.csv";
    
    private final Scanner scanner = new Scanner(System.in);

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
        this.recordedEvents = new Stack<>();
        this.vehiclesInTheCity = 0;
        this.reportsController = new ReportsController();
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
        if (this.city.getIncrasedCongestion() < node.getData().getComplexity()) this.city.setIncrasedCongestion(node.getData().getComplexity());
        this.vehiclesInTheCity++;
        this.vehicles.addElementAt(newVechicle, "");
        System.out.println("Vehiculo Registrado en el Sistema\n");
    }
    
    private void enterFile() {
        this.utilities.readTrafficFile(this.FILE_NAME, this.vehicles, this.coordinates);
        this.vehiclesInTheCity += this.vehicles.getSize();
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
            if (this.city.getIncrasedCongestion() < node.getData().getComplexity()) this.city.setIncrasedCongestion(node.getData().getComplexity());
            this.hashTable.insert(vehicle.getPlate(), vehicle);
        }
    }
    
    private void processIntersection(Intersection intersection) {
        //SE ACTUALIZA LA INTERSECCION ANTERIOR DEL VEHICULO
        Vehicle vehicle = intersection.reduceComplexity();
        //SE ACTUALIZA EL AVL
        this.avlTree.updateNode(intersection.getComplexity() + 1, intersection.getComplexity(), intersection.getId());
        //EL VEHICULO TIENE QUE MOVERSE
        vehicle.updateCurrentCoordinate();
        if (vehicle.getIsAtDestination()) {
            this.registerEvent(vehicle, intersection, null);
            this.vehiclesInTheCity--;
        } else {
            //SE ACTUALIZA LA INTERSECCION NUEVA DEL VEHICULO
            NodeMatrix<Intersection> newNode = this.city.putVehicle(vehicle, vehicle.getCurrentCoordinate());
            //SE ACTUALIZA EL AVL
            this.avlTree.updateNode(newNode.getData().getComplexity() - 1, newNode.getData().getComplexity(), newNode.getData().getId());
            this.registerEvent(vehicle, intersection, newNode.getData());
        }
        if (intersection.isCheckpoint()) {
            System.out.println("LA INTERSECCION " + intersection.getId() + " ES UN PUNTO DE CONTROL");
            int option = this.posters.menuViwReports();
            if (option == 1) this.generateReports();
        }
    }
    
    private void searchVehicle() {
        String plate;
        do {
            System.out.print("Ingrese la PLaca del Vehiculo a Buscar: ");
            plate = scanner.nextLine().toUpperCase();
            if (!this.utilities.validPlate(plate)) {
                this.posters.plateFormatWarning();
                continue;
            }
            break;
        } while (true);
        LinkedList<Vehicle> list = this.hashTable.get(plate);
        for (int i = 0; i < list.getSize(); i++) {
            Vehicle vehicle = list.getElementAt(i).getData();
            String coordinateX = this.utilities.convertCoordinate(vehicle.getCurrentCoordinate()[0] - 1);
            String coordinateY = vehicle.getCurrentCoordinate()[1] + "";
            System.out.println(vehicle.toString());
            System.out.println("Coordenada Actual: " + coordinateX + coordinateY);
        }
        System.out.println("");
    }
    
    public void execute() {
        int option = this.posters.mainMenu();
        switch (option) {
            case 1 -> this.start();
            case 2 -> System.out.println("---------- FIN DE LA APLICACION ----------");
        }
    }
    
    private void start() {
        this.posters.initialConfiguration();
        this.enterFile();
        this.city.printCity();
        System.out.println("---------- INICIO DE LA SIMULACION DE TRAFICO ----------");
        while (this.vehiclesInTheCity > 0) {
            int option2 = this.posters.simulationMenu();
            switch (option2) {
                case 1 -> this.manualSimulation();
                case 2 -> this.automaticSimulation();
                case 3 -> this.enterVechicle();
                case 4 -> this.searchVehicle();
            }
            this.city.printCity();
        }
        System.out.println("---------- SIMULACION TERMINADA ----------");
        int option4 = this.posters.menuViwReports();
        switch (option4) {
            case 1 -> this.generateReports();
            case 2 -> System.out.println("---------- CERRANDO SISTEMA ----------");
        }
    }
    
    private void manualSimulation() {
        System.out.println("----------- SIMULACION DE TRAFICO MANUAL -----------");
        System.out.println("-------- INTERSECCIONES MAS CONGESTIONADAS --------");
        boolean thereAreIntersections = false;
        int congestion = this.city.getIncrasedCongestion();
        while (!thereAreIntersections && congestion > 0) {
            LinkedList<TreeNode> mostCongestedIntersections = this.avlTree.search(congestion);
            for (int i = 0; i < mostCongestedIntersections.getSize(); i++) {
                Intersection intersection = (Intersection) mostCongestedIntersections.getElementAt(i).getData().getData();
                if (!intersection.getVehiclesWaiting().isEmpty()) {
                    intersection.printIntersection();
                    thereAreIntersections = true;
                }
            }
            congestion--;
        }
        System.out.println("----- QUE INTERSECCION QUIERE LIBERAR/PROCESAR? -----");
        System.out.print("Ingrese su opcion aqui: ");
        String coordinate = this.scanner.nextLine().toUpperCase();
        //BUSCAR LA INTERSECCION DENTRO DE LA MATRIZ
        int[] coordinateNumber = this.utilities.convertCoordinate(coordinate);
        NodeMatrix<Intersection> node = this.city.getMatrix().getNode(coordinateNumber[0] - 1, coordinateNumber[1] - 1);
        this.processIntersection(node.getData());
    }
    
    private void automaticSimulation() {
        System.out.println("--------------- SIMULACION DE TRAFICO AUTOMATICA ---------------");
        System.out.println("--- CUANTO TIEMPO/PASOS QUIERE AVANZAR EN LA SIMULACION? ---");
        System.out.print("Ingrese su opcion aqui: ");
        int cycles = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < cycles; i++) {
            if (this.vehiclesInTheCity <= 0) break;
            boolean thereAreIntersections = false;
            int congestion = this.city.getIncrasedCongestion();
            while (!thereAreIntersections && congestion > 0) {
                LinkedList<TreeNode> mostCongestedIntersections = this.avlTree.search(congestion);
                for (int j = 0; j < mostCongestedIntersections.getSize(); j++) {
                    Intersection intersection = (Intersection) mostCongestedIntersections.getElementAt(j).getData().getData();
                    if (!intersection.getVehiclesWaiting().isEmpty()) {
                        this.processIntersection(intersection);
                        thereAreIntersections = true;
                        break;
                    }
                }
                congestion--;
            }
        }
    }
    
    public void registerEvent(Vehicle vehicle, Intersection oldIntersection, Intersection newIntersection) {
        String description;
        if (newIntersection == null) {
            description = "EL VEHICULO " + vehicle.getPlate() + " LLEGO A SU DESTINO";
        } else {
            description = "EL VEHICULO " + vehicle.getPlate() + " CRUZO DEL PUNTO " + oldIntersection.getId() + " AL PUNTO " + newIntersection.getId();
        }
        System.out.println(description);
        System.out.println("");
        int priorityAttended = oldIntersection.getComplexity() + 1;
        for (int i : vehicle.getTotalPriority()) {
            priorityAttended += i;
        }
        Event newEvent = new Event(vehicle, priorityAttended, vehicle.getWaitingTime(), description);
        this.recordedEvents.push(newEvent);
    }
    
    public void generateReports() {
        int option;
        do {
            option = this.posters.reportsMenu();
            switch (option) {
                case 1 -> this.reportsController.vehicleRanking(this.vehicles);
                case 2 -> this.reportsController.numberOfVehiclesCrossed(this.city.getMatrix());
                case 3 -> this.reportsController.averageVehicleWaitingTime(this.vehicles);
                case 4 -> this.reportsController.intersectionGraph(this.avlTree);
                case 5 -> this.reportsController.duplicatePlatesConflict(this.hashTable);
                case 6 -> this.reportsController.latestRelevantEvents(this.recordedEvents);
            }
        } while (option != 7);
    }
    
}
