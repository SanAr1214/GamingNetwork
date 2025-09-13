/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gnbd?useUnicode=true&characterEncoding=UTF-8";
;
    private static final String USER = "root";
    private static final String PASSWORD = "Sena2024!";
    
    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado a MySQL");
            return conexion;
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
            return null;
        }
    }
}
