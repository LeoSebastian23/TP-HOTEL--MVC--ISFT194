package Models.DAO;
import Models.HabitacionModel;
import Models.connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase `HabitacionDAO` es una implementación de la interfaz `DAO` que se encarga de gestionar
 * las operaciones de acceso a datos relacionadas con las habitaciones en la base de datos.
 *
 * Esta clase hereda de `connexion`, lo que le permite acceder a la configuración de conexión
 * a la base de datos.
 *
 **/
public class HabitacionDAO extends connexion implements DAO<HabitacionModel> {
    private static HabitacionDAO instance; // Instancia Singleton
    private List<HabitacionModel> habitacionesList; // Lista para manejar las habitaciones

    // Constructor privado para evitar la creación de nuevas instancias desde fuera
    private HabitacionDAO() {
        this.habitacionesList = new ArrayList<>();
        cargarHabitaciones(); // Cargar datos desde la base de datos a la lista
    }

    // Método estático para obtener la única instancia de HabitacionDAO
    public static HabitacionDAO getInstance() {
        if (instance == null) {
            instance = new HabitacionDAO();
        }
        return instance;
    }

    // Método para cargar habitaciones de la base de datos a la lista
    private void cargarHabitaciones() {
        String sql = "SELECT * FROM habitacion";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Recorrer cada fila del resultado y agregar las habitaciones a la lista
            while (resultSet.next()) {
                // Extraer los datos de cada columna y crear un nuevo objeto HabitacionModel
                int id = resultSet.getInt("id");
                int numeroHabitacion = resultSet.getInt("numeroHabitacion");
                int cantidadCamas = resultSet.getInt("cantidadCamas");
                int cantidadCamasSimples = resultSet.getInt("cantidadCamasSimples");
                int cantidadCamasDobles = resultSet.getInt("cantidadCamasDobles");
                int precioDia = resultSet.getInt("precioDia");

                // Añadir la habitación a la lista
                habitacionesList.add(new HabitacionModel(id, numeroHabitacion, cantidadCamas, cantidadCamasSimples, cantidadCamasDobles, precioDia));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear una habitación en la base de datos
    @Override
    public void create(HabitacionModel dato) {
        String sql = "INSERT INTO habitacion (numeroHabitacion, cantidadCamas, cantidadCamasSimples, cantidadCamasDobles, precioDia) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dato.getNumeroHabitacion());
            statement.setInt(2, dato.getCantidadCamas());
            statement.setInt(3, dato.getCantidadCamasSimples());
            statement.setInt(4, dato.getCantidadCamasDobles());
            statement.setInt(5, dato.getPrecioDia());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todas las habitaciones de la base de datos
    @Override
    public List<HabitacionModel> getAll() {
        List<HabitacionModel> habitacionModels = new ArrayList<>();
        String sql = "SELECT * FROM habitacion";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int numeroHabitacion = resultSet.getInt("numeroHabitacion");
                int cantidadCamas = resultSet.getInt("cantidadCamas");
                int cantidadCamasSimples = resultSet.getInt("cantidadCamasSimples");
                int cantidadCamasDobles = resultSet.getInt("cantidadCamasDobles");
                int precioDia = resultSet.getInt("precioDia");

                habitacionModels.add(new HabitacionModel(id, numeroHabitacion, cantidadCamas, cantidadCamasSimples, cantidadCamasDobles, precioDia));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitacionModels;
    }

    // Método para obtener una habitación por su ID
    @Override
    public HabitacionModel getById(int id) {
        HabitacionModel habitacionModel = null;
        String sql = "SELECT * FROM habitacion WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id); // Usar el parámetro del método

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Cambiamos el nombre de la variable que se usa para extraer el ID de la base de datos
                int idHabitacion = resultSet.getInt("id");
                int numeroHabitacion = resultSet.getInt("numeroHabitacion");
                int cantidadCamas = resultSet.getInt("cantidadCamas");
                int cantidadCamasSimples = resultSet.getInt("cantidadCamasSimples");
                int cantidadCamasDobles = resultSet.getInt("cantidadCamasDobles");
                int precioDia = resultSet.getInt("precioDia");

                // Crear un nuevo objeto HabitacionModel con los datos obtenidos
                habitacionModel = new HabitacionModel(idHabitacion, numeroHabitacion, cantidadCamas, cantidadCamasSimples, cantidadCamasDobles, precioDia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return habitacionModel;
    }


    // Método para actualizar una habitación
    @Override
    public void update(HabitacionModel dato, int id) {
        String sql = "UPDATE habitacion SET numeroHabitacion = ?, cantidadCamas = ?, cantidadCamasSimples = ?, cantidadCamasDobles = ?, precioDia = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dato.getNumeroHabitacion());
            statement.setInt(2, dato.getCantidadCamas());
            statement.setInt(3, dato.getCantidadCamasSimples());
            statement.setInt(4, dato.getCantidadCamasDobles());
            statement.setInt(5, dato.getPrecioDia());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una habitación
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM habitacion WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
