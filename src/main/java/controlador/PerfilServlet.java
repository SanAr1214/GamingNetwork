/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import dao.UsuarioDao;
import modelo.usuario;

/**
 *
 * @author santi
 */
@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

    private UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
    usuario u = usuarioDao.getById(idUsuario);

    if (u != null) {
        request.setAttribute("usuario", u);
        request.getRequestDispatcher("/vista/perfil.jsp").forward(request, response); // <- Aquí es importante
    } else {
        response.sendRedirect(request.getContextPath() + "/inicioUsuario.jsp");
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String nickname = request.getParameter("nickname");
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");

        usuario u = new usuario();
        u.setIdUsuario(idUsuario);
        u.setNombre(nombre);
        u.setNickname(nickname);
        u.setCorreo(correo);
        if (contraseña != null && !contraseña.isEmpty()) {
            u.setContraseña(contraseña);
        }

        boolean actualizado = usuarioDao.actualizarUsuario(u);

        if (actualizado) {
            response.sendRedirect("perfil?idUsuario=" + idUsuario);
        } else {
            request.setAttribute("error", "No se pudo actualizar el perfil.");
            request.setAttribute("usuario", u);
            request.getRequestDispatcher("/vista/perfil.jsp").forward(request, response);
        }
    }
}
