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

import estacion.*;

/**
 *
 * @author santi
 */
public class eliminar {
     public static void main (String[] args){
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
    
        int id_eliminar = 2;
    
        String sql = "DELETE FROM reserva WHERE id_reserva=" + id_eliminar;
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);    
        }
    
        try{
            cn = con.getConection();
            st = cn.createStatement();
            st.executeUpdate(sql);
            rs = st.executeQuery("SELECT * FROM reserva ");
            rs.next();
    
            do{
                System.out.println(
                    rs.getInt("id_reserva")+":"+
                    rs.getInt("id_usuario")+"-"+
                    rs.getInt("id_estacion")+"-"+
                    rs.getString("fecha")+"-"+
                    rs.getString("hora_inicio")+"-"+
                    rs.getInt("duracion")
                );
            }while (rs.next());
    
        }catch (SQLException ex){
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}

