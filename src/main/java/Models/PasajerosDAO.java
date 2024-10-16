package Models;
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

 public class PasajerosDAO extends connexion implements DAO<Pasajero> {
    // Método para crear un pasajero en la base de datos
    @Override
    public void create(Pasajero dato) {
        String sql = "INSERT INTO pasajeros (dni, name, surname, cel, mail) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dato.getDni());
            statement.setString(2, dato.getName());
            statement.setString(3, dato.getSurname());
            statement.setInt(4, dato.getCel());
            statement.setString(5, dato.getMail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los pasajeros
    @Override
    public List<Pasajero> getAll() {
        List<Pasajero> pasajeros = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int dni = resultSet.getInt("dni");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int cel = resultSet.getInt("cel");
                String mail = resultSet.getString("mail");

                pasajeros.add(new Pasajero(id, dni, name, surname, cel, mail));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajeros;
    }

    // Método para obtener un pasajero por su ID
    @Override
    public Pasajero getById(int id) {
        Pasajero pasajero = null;
        String sql = "SELECT * FROM pasajeros WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int dni = resultSet.getInt("dni");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int cel = resultSet.getInt("cel");
                String mail = resultSet.getString("mail");

                pasajero = new Pasajero(id, dni, name, surname, cel, mail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pasajero;
    }

    // Método para actualizar un pasajero
    @Override
    public void update(Pasajero dato, int id) {
        String sql = "UPDATE pasajeros SET dni = ?, name = ?, surname = ?, cel = ?, mail = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dato.getDni());
            statement.setString(2, dato.getName());
            statement.setString(3, dato.getSurname());
            statement.setInt(4, dato.getCel());
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
        String sql = "DELETE FROM pasajeros WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

