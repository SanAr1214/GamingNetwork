/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.enterprise.concurrent.ManagedExecutorDefinition.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.DBConnection;
import modelo.usuario;

/**
 *
 * @author santi
 */
public class UsuarioDao {
    
    private Connection conexion;

    public UsuarioDao() {
        this.conexion = Conexion.conectar();
    }

    //Funcion insertar usuario
    public boolean guardarUsuario(usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, nickname, correo, contraseña, rol) VALUES (?,?,?,?,?)";
        try (Connection conexion = DBConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getNickname());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContraseña());
            stmt.setString(5, usuario.getRol());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metodo validar inicio de sesión
    public usuario iniciarSesion(String nickname, String contraseña) {
    String sql = "SELECT * FROM usuarios WHERE nickname = ? AND contraseña = ?";
    try (Connection conexion = DBConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setString(1, nickname);
        stmt.setString(2, contraseña);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario usuario = new usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setNickname(rs.getString("nickname"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setContraseña(rs.getString("contraseña"));
            usuario.setRol(rs.getString("rol"));
            return usuario; // si lo recibe devuelve un objeto usuario
        }
    } catch (SQLException e) {
        System.out.println("Error al iniciar sesión: " + e.getMessage());
    }
    return null; // si no encuentra usuario se devuelve un null
}
    
    public usuario getById(int idUsuario) {
    String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
    try (Connection conexion = DBConnection.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario u = new usuario();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setNombre(rs.getString("nombre"));
            u.setNickname(rs.getString("nickname"));
            u.setCorreo(rs.getString("correo"));
            u.setContraseña(rs.getString("contraseña"));
            u.setRol(rs.getString("rol"));
            return u; // Devuelve el usuario encontrado
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // Si no encuentra usuario
}

    
    
    public boolean actualizarUsuario(usuario usuario) {
    // Construimos SQL dinámico para no actualizar la contraseña si viene vacía
    StringBuilder sql = new StringBuilder("UPDATE usuarios SET nombre = ?, nickname = ?, correo = ?");
    boolean actualizarPassword = usuario.getContraseña() != null && !usuario.getContraseña().trim().isEmpty();
    if (actualizarPassword) {
        sql.append(", contraseña = ?");
    }
    sql.append(" WHERE id_usuario = ?");

    try (Connection conexion = DBConnection.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(sql.toString())) {

        int idx = 1;
        stmt.setString(idx++, usuario.getNombre());
        stmt.setString(idx++, usuario.getNickname());
        stmt.setString(idx++, usuario.getCorreo());

        if (actualizarPassword) {
            // RECOMENDACIÓN: aquí deberías pasar la contraseña ya hasheada (BCrypt) desde el servlet.
            stmt.setString(idx++, usuario.getContraseña());
        }

        stmt.setInt(idx, usuario.getIdUsuario()); // id_usuario al final

        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

// Método para eliminar el usuario por id (usado con el id de la sesión)
public boolean eliminarUsuario(int idUsuario) {
    String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
    try (Connection conexion = DBConnection.getConnection();
         PreparedStatement stmt = conexion.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);
        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
  

       
}

   


