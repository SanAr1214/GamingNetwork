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
public class editar {
    
    
    public static void main (String[] args){
        conexion con = new conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
        
        int id_editar = 2;
        String new_tipo = "PC Estándar";
        String new_descripccion = "Computador de uso básico para oficina";
        String new_ubicacion = "Zona B - Estación 3";
        String new_estado = "Mantenimiento";

        String sql = "UPDATE estacion SET tipo='" + new_tipo + "', descripccion='" + new_descripccion + 
                     "', ubicacion='" + new_ubicacion + "', estado='" + new_estado + "' WHERE id_estacion=" + id_editar;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);    
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
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}

    

