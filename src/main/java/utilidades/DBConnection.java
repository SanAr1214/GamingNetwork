/*
 * esta clase se encarga de manejar la conexion con la base de datos
 */
package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class DBConnection {
    // datos de la conexion (url, usuario y clave)
    private static final String URL = "jdbc:mysql://localhost:3306/gnbd";
    private static final String USER = "root";
    private static final String PASS = "Sena2024!";
    
    // este metodo abre la conexion a la base de datos
    public static Connection getConnection() throws SQLException {
        try {
            // cargo el driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // si no encuentra el driver lanza error
            throw new SQLException("Driver no encontrado: " + e.getMessage(), e);
        }
        // retorno la conexion usando los datos
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * metodo para probar si la conexion funciona
     * @return true si conecta, false si falla
     */
    public static boolean testConnection() {
        try (Connection c = getConnection()) {
            // si la conexion no es nula y no esta cerrada, entonces sirve
            return c != null && !c.isClosed();
        } catch (SQLException e) {
            // si algo falla muestro el error y retorno false
            e.printStackTrace();
            return false;
        }
    }
}
