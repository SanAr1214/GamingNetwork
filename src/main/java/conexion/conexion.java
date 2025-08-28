/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author santi
 */

public class conexion {
    
    public Connection getConection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gnbd", 
                "root",                             
                "Sena2024!"                        
            );
            System.out.println("✅ Conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Error en la conexion: " + e.getMessage());
        }
        return con;
    }
}