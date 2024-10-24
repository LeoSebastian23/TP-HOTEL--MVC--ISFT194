package org.example;

import Controllers.PasajeroController;
import Views.PasajerosView;

public class Main {
    public static void main(String[] args) {
        /*// Crear una instancia del PasajeroController
        PasajeroController pasajeroController = new PasajeroController();

        // Crear dos pasajeros para probar el sistema
        PasajeroModel pasajero1 = new PasajeroModel(0, 12345678, "Juan", "Pérez", 1112345678, "juan.perez@example.com");
        PasajeroModel pasajero2 = new PasajeroModel(0, 87654321, "María", "García", 1198765432, "maria.garcia@example.com");

        // Agregar los pasajeros a la base de datos
        pasajeroController.addPasajero(pasajero1);
        pasajeroController.addPasajero(pasajero2);

        // Recuperar y mostrar todos los pasajeros de la base de datos
        List<PasajeroModel> pasajeros = pasajeroController.getAllPasajeros();
        for (PasajeroModel pasajero : pasajeros) {
            System.out.println("ID: " + pasajero.getId());
            System.out.println("DNI: " + pasajero.getDni());
            System.out.println("Nombre: " + pasajero.getName());
            System.out.println("Apellido: " + pasajero.getSurname());
            System.out.println("Celular: " + pasajero.getCel());
            System.out.println("Email: " + pasajero.getMail());
            System.out.println("---------------");
        }*/
                PasajeroController pasajeroController = new PasajeroController();
                PasajerosView pasajeroView = new PasajerosView(pasajeroController);
        }
    }