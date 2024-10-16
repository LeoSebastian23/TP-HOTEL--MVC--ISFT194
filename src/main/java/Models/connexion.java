package Models;
/**
 * La clase abstracta `connexion` se utiliza para gestionar la configuración de conexión a la base de datos.
 *
 * Esta clase define las credenciales necesarias para establecer una conexión con la base de datos MySQL
 * que se está utilizando en la aplicación, así como la URL de la base de datos.
 *
 * Como esta clase es abstracta, no puede ser instanciada directamente. En su lugar, otras clases
 * (como `PasajerosDAO`) pueden extender esta clase y utilizar estos atributos para establecer conexiones
 * a la base de datos cuando necesiten realizar operaciones CRUD.
 */
public abstract class connexion {
    protected final String URL = "jdbc:mysql://localhost:3306/HotelTito";
    protected final String USER = "root";
    protected final String PASSWORD = "panza2021";
}
