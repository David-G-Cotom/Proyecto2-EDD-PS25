/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_edd_ps25.controllers;

import com.mycompany.proyecto2_edd_ps25.models.Event;
import com.mycompany.proyecto2_edd_ps25.models.Intersection;
import com.mycompany.proyecto2_edd_ps25.models.Vehicle;
import com.mycompany.proyecto2_edd_ps25.models.VehicleType;
import com.mycompany.proyecto2_edd_ps25.structs.hash.HashTable;
import com.mycompany.proyecto2_edd_ps25.structs.list.LinkedList;
import com.mycompany.proyecto2_edd_ps25.structs.list.NodeList;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.NodeMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.matrix.OrthogonalMatrix;
import com.mycompany.proyecto2_edd_ps25.structs.stack.Stack;
import com.mycompany.proyecto2_edd_ps25.structs.tree.AVLTree;
import com.mycompany.proyecto2_edd_ps25.structs.tree.TreeNode;
import com.mycompany.proyecto2_edd_ps25.utils.SortingAlgorithms;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Carlos Cotom
 */
public class ReportsController {
    
    private final int NUMBER_OF_RELEVANT_EVENTS = 20;
    
    private final SortingAlgorithms sortingAlgorithms;

    public ReportsController() {
        this.sortingAlgorithms = new SortingAlgorithms();
    }
    
    public void vehicleRanking(LinkedList<Vehicle> vehicles) {
        System.out.println("---------- RRANKING DE VEHICULOS POR PRIORIDAD Y TIEMPO DE ESPERA ----------");
        this.sortingAlgorithms.insertionSort(vehicles);
        NodeList<Vehicle> node = vehicles.getHead();
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
        System.out.println("");
    }
    
    public void numberOfVehiclesCrossed(OrthogonalMatrix<Intersection> matrix) {
        System.out.println("--------------- CANTIDAD DE VEHICULOS QUE HAN CRUZADO POR INTERSECCION ---------------");
        NodeMatrix<Intersection> aux = matrix.getRoot();
        for (int i = 0; i < matrix.getDimensionX(); i++) {
            for (int j = 0; j < matrix.getDimensionY(); j++) {
                System.out.print("Interseccion: " + aux.getData().getId());
                System.out.println(", cruzaron/han estado: " + aux.getData().getNumberOfVehiclesCrossed() + " vehiculos");
                aux = aux.getNext();
            }
            aux = matrix.getNode(i, 0);
            aux = aux.getBottom();
        }
        System.out.println("");
    }
    
    public void averageVehicleWaitingTime(LinkedList<Vehicle> vehicles) {
        System.out.println("---------- TIEMPO PROMEDIO DE ESPERA POR TIPO DE VEHICULO ----------");
        LinkedList<int[]> list = new LinkedList<>();
        int[] time = new int[VehicleType.values().length];
        int[] numberOfVehicles = new int[VehicleType.values().length];
        for (int i = 0; i < vehicles.getSize(); i++) {
            Vehicle vehicle = vehicles.getElementAt(i).getData();
            time[vehicle.getVehicleType().ordinal()] += vehicle.getWaitingTime();
            numberOfVehicles[vehicle.getVehicleType().ordinal()]++;
        }
        for (int i = 0; i < VehicleType.values().length; i++) {
            int[] values = new int[2];
            values[0] = i;
            if (numberOfVehicles[i] == 0) {
                values[1] = 0;
            } else {
                values[1] = time[i]/numberOfVehicles[i];
            }
            list.addElementAt(values, "");
        }
        this.sortingAlgorithms.shaker_cocktail_Sort(list);
        for (int i = 0; i < VehicleType.values().length; i++) {
            if (list.getElementAt(i).getData()[1] == 0) {
                System.out.println(VehicleType.values()[list.getElementAt(i).getData()[0]].toString() + ": 0");
            }
            System.out.println(VehicleType.values()[list.getElementAt(i).getData()[0]].toString() + ": " + list.getElementAt(i).getData()[1]);
        }
        System.out.println("");
    }
    
    public void intersectionGraph(AVLTree<Intersection> avlTree) {
        avlTree.print(5);
        String dot = "digraph{\nrankdir = TB;\nlabel = \"Arbol de Intersecciones\";\nlabelloc = t;\nnode[color=transparent style=filled];\nnode[shape = box];\nnode[shape = circle];\n";
        dot += this.traversePreOrden(avlTree.getRoot(), dot);
        dot += "\n}";
        Path outputDir = Paths.get("../Graficas");
        try {
            Files.createDirectories(outputDir);
            try (FileWriter file = new FileWriter("../Graficas/ReporteArbolAVL.txt")) {
                file.write(dot);
            }
            Process process = Runtime.getRuntime().exec(new String[] {
                "dot",
                "-Tpng",
                "../Graficas/ReporteArbolAVL.txt",
                "-o",
                "../Graficas/ReporteArbolAVL.png"
            });
            process.waitFor();
            System.out.println("\n>> Reporte Generado!!!");
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private String traversePreOrden(TreeNode<Intersection> node, String dot) {
        String dotAux = "";
        if (node != null) {
            dotAux += "\"" + node.getId()
            + "\"[label=<Interseccion" + node.getId()
            + "<br/>Complejidad: " + node.getSize()
            + "> fillcolor=\"#8080F0\"];\n";
            if (node.getLeft() != null) {
                dotAux += traversePreOrden(node.getLeft(), dotAux) + "\""
                + node.getId() + "\" -> \"" + node.getLeft().getId()
                + "\";\n";
            }
            if (node.getRight() != null) {
                dotAux += traversePreOrden(node.getRight(), dotAux) + "\""
                + node.getId() + "\" -> \"" + node.getRight().getId()
                + "\";\n";
            }
        }
        return dotAux;
    }
    
    public void duplicatePlatesConflict(HashTable<Vehicle> hashTable) {
        System.out.println("--------------- PLACAS DUPLICADA/CONFLICTO ---------------");
        LinkedList<Vehicle> list = hashTable.getCollisions();
        if (list.isEmpty()) {
            System.out.println("NO HAY PLACAS DUPLICADAS/CONFLICTO EN EL SISTEMA\n");
            return;
        }
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getElementAt(i).getData().toString());
        }
        System.out.println("");
    }
    
    public void latestRelevantEvents(Stack<Event> recordedEvents) {
        System.out.println("--------------- ULTIMOS 20 EVENTOS DEL SISTEMA ---------------");
        Stack<Event> auxStack = new Stack<>();
        for (int i = 0; i < this.NUMBER_OF_RELEVANT_EVENTS; i++) {
            Event event = recordedEvents.pop();
            if (event == null) {
                System.out.println("NO HAY MAS EVENTOS REGISTRADOS POR EL MOMENTO");
                break;
            }
            System.out.println(event.toString());
            auxStack.push(event);
        }
        while (true) {
            recordedEvents.push(auxStack.pop());
            if (auxStack.isEmpty()) break;
        }
        System.out.println("");
    }
    
}
