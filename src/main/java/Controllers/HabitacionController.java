package Controllers;

import Models.HabitacionModel;
import Models.DAO.HabitacionDAO;
import java.util.List;

public class HabitacionController {
    private final HabitacionDAO habitacionDAO;

    // Constructor que utiliza el Singleton del PasajeroDAO
    public HabitacionController() {
        this.habitacionDAO = HabitacionDAO.getInstance();
    }

    // Crear un nuevo pasajero
    public void addHabitacion(HabitacionModel habitacionModel) {
        habitacionDAO.create(habitacionModel);
        System.out.println("Habitacion añadida con éxito: " + habitacionModel.getNumeroHabitacion());
    }

    // Obtener la lista de todos los pasajeros
    public List<HabitacionModel> getAllHabitacion() {
        return habitacionDAO.getAll();
    }

    // Buscar un pasajero por ID
    public HabitacionModel findHabitacionById(int id) {
        return habitacionDAO.getById(id);
    }

    // Actualizar los datos de un pasajero
    public void updateHabitacion(HabitacionModel habitacionModel, int id) {
        habitacionDAO.update(habitacionModel, id);
        System.out.println("Habitacion actualizado con éxito: " + habitacionModel.getNumeroHabitacion());
    }

    // Eliminar un pasajero por ID
    public void deleteHabitacion(int id) {
        habitacionDAO.delete(id);
        System.out.println("Habitacion eliminado con éxito, ID: " + id);
    }
}