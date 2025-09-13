/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author santi
 */
public class usuario {
    private int idUsuario;
    private String nombre;
    private String nickname;
    private String correo;
    private String contraseña;
    private String rol;
    
    public usuario() {}
    

    public usuario(String nombre, String nickname, String correo, String contraseña, String rol) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;   
    }

    public usuario(int idUsuario, String nickname, String nombre, String correo, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.nickname = nickname;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
