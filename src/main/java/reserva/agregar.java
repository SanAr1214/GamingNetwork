/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reserva;

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

        int id_usuario = 1; // debe ESTAR en la tabla usuario
        int id_estacion = 2; // debe ESTAR en la tabla estacion
        String fecha = "2025-08-27";
        String hora_inicio = "15:00:00";
        int duracion = 2;

        String sql = "INSERT INTO reserva (id_usuario, id_estacion, fecha, hora_inicio, duracion) " +
                     "values (" + id_usuario + "," + id_estacion + ",'" + fecha + "','" + hora_inicio + "'," + duracion + ")";

        try{
            cn = con.getConection();
            st = cn.createStatement();
            st.executeUpdate(sql);
            rs = st.executeQuery("SELECT * FROM reserva ");
            rs.next();

            do{
                System.out.println(
                    rs.getInt("id_reserva") + ":" +
                    rs.getInt("id_usuario") + "-" +
                    rs.getInt("id_estacion") + "-" +
                    rs.getString("fecha") + "-" +
                    rs.getString("hora_inicio") + "-" +
                    rs.getInt("duracion")
                );
            }while (rs.next());

        }catch (SQLException ex){
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }


