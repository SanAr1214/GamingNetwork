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
public class eliminar {
     public static void main (String[] args){
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
    
        int id_eliminar = 3;
    
        String sql = "DELETE FROM estacion WHERE id_estacion=" + id_eliminar;
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);    
        }
    
        try{
            cn = con.getConection();
            st = cn.createStatement();
            st.executeUpdate(sql);
            rs = st.executeQuery("SELECT * FROM estacion ");
            rs.next();
    
            do{
                System.out.println(
                    rs.getInt("id_estacion")+":"+
                    rs.getString("tipo")+"-"+
                    rs.getString("descripccion")+"-"+
                    rs.getString("ubicacion")+"-"+
                    rs.getString("estado")
                );
            }while (rs.next());
    
        }catch (SQLException ex){
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}

