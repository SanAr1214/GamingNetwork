/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario;
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
    conexion con=new conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    
        String nombre = "Liam";
        String correo = "liam13@example.com";
        String contraseña = "lim2025!!";
        String rol = "cliente";

    String sql = "INSERT INTO usuario (nombre, correo, contraseña, rol) values ('" + nombre + "','" + correo + "','" + contraseña + "','" + rol + "')";
    
    try{
    cn=con.getConection();
    st=cn.createStatement();
    st.executeUpdate(sql);
    rs=st.executeQuery("SELECT * FROM usuario ");
    rs.next();
    
    do{
    System.out.println(rs.getInt("id_usuario")+":"+rs.getString("nombre")+"-"+rs.getString("correo")+"-"+rs.getString("contraseña")+"-"+rs.getString("rol"));
    
    }while (rs.next());
    
    
    }catch (SQLException ex){
        Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }   
}
    

