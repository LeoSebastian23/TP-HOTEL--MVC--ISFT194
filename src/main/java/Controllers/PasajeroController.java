package Controllers;

import Models.PasajeroModel;
import Models.DAO.PasajeroDAO;
import java.util.List;

public class PasajeroController {
    private final PasajeroDAO pasajerosDAO;

    // Constructor que utiliza el Singleton del PasajeroDAO
    public PasajeroController() {
        this.pasajerosDAO = PasajeroDAO.getInstance();
    }

    // Crear un nuevo pasajero
    public void addPasajero(PasajeroModel pasajeroModel) {
        pasajerosDAO.create(pasajeroModel);
        System.out.println("Pasajero añadido con éxito: " + pasajeroModel.getName());
    }

    // Obtener la lista de todos los pasajeros
    public List<PasajeroModel> getAllPasajeros() {
        return pasajerosDAO.getAll();
    }

    // Buscar un pasajero por ID
    public PasajeroModel findPasajeroById(int id) {
        return pasajerosDAO.getById(id);
    }

    // Actualizar los datos de un pasajero
    public void updatePasajero(PasajeroModel pasajeroModel, int id) {
        pasajerosDAO.update(pasajeroModel, id);
        System.out.println("Pasajero actualizado con éxito: " + pasajeroModel.getName());
    }

    // Eliminar un pasajero por ID
    public void deletePasajero(int id) {
        pasajerosDAO.delete(id);
        System.out.println("Pasajero eliminado con éxito, ID: " + id);
    }
}

