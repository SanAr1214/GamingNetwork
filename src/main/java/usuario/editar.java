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
public class editar {
    
    public static void main (String[] args){
    conexion con=new conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    
    int id_editar=2;
    String new_nombre = "Ana Rodriguez";
    String new_correo = "anar@example.com";
    String new_contraseña = "tati12345";
    String new_rol = "admin";
    String sql = "UPDATE usuario SET nombre='" + new_nombre + "', correo='" + new_correo + "', contraseña='" + new_contraseña + "', rol='" + new_rol + "' WHERE id_usuario=" + id_editar;
    try{
     Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException ex){
        Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);    
    }
    try{
    cn=con.getConection();
    st=cn.createStatement();
    st.executeUpdate(sql);
    rs=st.executeQuery("SELECT * FROM usuario ");
    rs.next();
    
    do{
    System.out.println(rs.getInt("id_usuario")+":"+rs.getString("nombre")+"-"+rs.getString("correo")+"-"+rs.getString("contraseña")+rs.getString("rol")+"-");
    
    }while (rs.next());
    
    
    }catch (SQLException ex){
        Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }   
}
    

