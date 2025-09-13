/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
    private static final String URL = "jdbc:mysql://localhost:3306/gnbd";
    private static final String USER = "root";
    private static final String PASS = "Sena2024!";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado: " + e.getMessage(), e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * Metodo testear conexion
     * @return 
     */
    public static boolean testConnection() {
        try (Connection c = getConnection()) {
            return c != null && !c.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
