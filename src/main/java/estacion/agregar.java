/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacion;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santi
 */
public class agregar {
    
    public static void main (String[] args){
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;

        String tipo = "PC Gamer";
        String descripcion = "Computador de alto rendimiento con tarjeta gráfica RTX";
        String ubicacion = "Zona A - Estación 5";
        String estado = "Disponible";

        String sql = "INSERT INTO estacion (tipo, descripccion, ubicacion, estado) values ('" 
                     + tipo + "','" + descripcion + "','" + ubicacion + "','" + estado + "')";

        try{
            cn = con.getConection();
            st = cn.createStatement();
            st.executeUpdate(sql);
            rs = st.executeQuery("SELECT * FROM estacion ");
            rs.next();

            do{
                System.out.println(
                    rs.getInt("id_estacion") + ":" +
                    rs.getString("tipo") + "-" +
                    rs.getString("descripccion") + "-" +
                    rs.getString("ubicacion") + "-" +
                    rs.getString("estado")
                );
            }while (rs.next());

        }catch (SQLException ex){
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}

    

