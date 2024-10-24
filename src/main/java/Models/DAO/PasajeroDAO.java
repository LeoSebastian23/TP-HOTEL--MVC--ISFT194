package Models.DAO;
import Models.PasajeroModel;
import Models.connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * La clase `PasajerosDAO` es una implementación de la interfaz `DAO` que se encarga de gestionar
 * las operaciones de acceso a datos relacionadas con los pasajeros en la base de datos.
 *
 * Esta clase hereda de `connexion`, lo que le permite acceder a la configuración de conexión
 * a la base de datos.
 *
 * **/

 public class PasajeroDAO extends connexion implements DAO<PasajeroModel> {
    private static PasajeroDAO instance; // Instancia Singleton
    private List<PasajeroModel> pasajerosList; // Lista para manejar los pasajeros

    // Constructor privado para evitar la creación de nuevas instancias desde fuera
    private PasajeroDAO() {
        this.pasajerosList = new ArrayList<>();
        cargarPasajeros(); // Cargar datos desde la base de datos a la lista
    }

    // Método estático para obtener la única instancia de PasajerosDAO
    public static PasajeroDAO getInstance() {
        if (instance == null) {
            instance = new PasajeroDAO();
        }
        return instance;
    }

    // Método para cargar pasajeros de la base de datos a la lista
    private void cargarPasajeros() {
        String sql = "SELECT * FROM pasajero";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Recorrer cada fila del resultado y agregar los pasajeros a la lista
            while (resultSet.next()) {
                // Extraer los datos de cada columna y crear un nuevo Pasajero
                int id = resultSet.getInt("id");
                int dni = resultSet.getInt("dni");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String cel = resultSet.getString("cel");
                String mail = resultSet.getString("mail");

                // Añadir el pasajero a la lista
                pasajerosList.add(new PasajeroModel(id, dni, name, surname, cel, mail));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para crear un pasajero en la base de datos y guardarlo en la base de datos.
    @Override
    public void create(PasajeroModel dato) {
        String sql = "INSERT INTO pasajero (dni, name, surname, cel, mail) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dato.getDni());
            statement.setString(2, dato.getName());
            statement.setString(3, dato.getSurname());
            statement.setString(4, dato.getCel());
            statement.setString(5, dato.getMail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los pasajeros de la lista
    @Override
    public List<PasajeroModel> getAll() {
        List<PasajeroModel> pasajeroModels = new ArrayList<>();
        String sql = "SELECT * FROM pasajero";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int dni = resultSet.getInt("dni");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String cel = resultSet.getString("cel");
                String mail = resultSet.getString("mail");

                pasajeroModels.add(new PasajeroModel(id, dni, name, surname, cel, mail));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajeroModels;
    }

    // Método para obtener un pasajero por su ID
    @Override
    public PasajeroModel getById(int id) {
        PasajeroModel pasajeroModel = null;
        String sql = "SELECT * FROM pasajero WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int dni = resultSet.getInt("dni");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String cel = resultSet.getString("cel");
                String mail = resultSet.getString("mail");

                pasajeroModel = new PasajeroModel(id, dni, name, surname, cel, mail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajeroModel;
    }

    // Método para actualizar un pasajero
    @Override
    public void update(PasajeroModel dato, int id) {
        String sql = "UPDATE pasajero SET dni = ?, name = ?, surname = ?, cel = ?, mail = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dato.getDni());
            statement.setString(2, dato.getName());
            statement.setString(3, dato.getSurname());
            statement.setString(4, dato.getCel());
            statement.setString(5, dato.getMail());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un pasajero
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM pasajero WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

