/*
 * Clase que maneja todo lo de usuarios en la base de datos
 */
package dao;

import jakarta.enterprise.concurrent.ManagedExecutorDefinition.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.DBConnection;
import modelo.usuario;

public class UsuarioDao {
    
    private Connection conexion;

    // Constructor que abre la conexion
    public UsuarioDao() {
        this.conexion = Conexion.conectar(); // aca conecto a la BD
    }

    // Guardar un usuario nuevo
    public boolean guardarUsuario(usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, nickname, correo, contraseña, rol) VALUES (?,?,?,?,?)";
        try (Connection conexion = DBConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            // lleno los valores con los datos del usuario
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getNickname());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContraseña());
            stmt.setString(5, usuario.getRol());

            // si guardo algo, devuelvo true
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // muestro error en consola
            return false;
        }
    }

    // Iniciar sesion: reviso si existe un usuario con ese nickname y contraseña
    public usuario iniciarSesion(String nickname, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE nickname = ? AND contraseña = ?";
        try (Connection conexion = DBConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            stmt.setString(1, nickname);
            stmt.setString(2, contraseña);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // si lo encontro, armo el objeto usuario y lo devuelvo
                usuario usuario = new usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setNickname(rs.getString("nickname"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
        }
        return null; // si no existe, devuelvo null
    }
    
    // Buscar un usuario por id
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
                return u; // devuelvo el usuario que encontre
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // si no encontro nada
    }

    // Actualizar usuario
    public boolean actualizarUsuario(usuario usuario) {
        // si la contraseña esta vacia, no la actualizo
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
                // aca deberia ir la contraseña encriptada
                stmt.setString(idx++, usuario.getContraseña());
            }

            stmt.setInt(idx, usuario.getIdUsuario());

            int filas = stmt.executeUpdate();
            return filas > 0; // true si se actualizo

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un usuario por id
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conexion = DBConnection.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            int filas = stmt.executeUpdate();
            return filas > 0; // true si borro algo

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
