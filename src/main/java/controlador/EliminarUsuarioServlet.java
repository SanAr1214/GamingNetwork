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

/**
 *
 * @author santi
 */


@WebServlet("/eliminarUsuario")
public class EliminarUsuarioServlet extends HttpServlet {

    private UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        boolean eliminado = usuarioDao.eliminarUsuario(idUsuario);

        if (eliminado) {
            response.sendRedirect("inicioUsuario.jsp");
        } else {
            request.setAttribute("error", "No se pudo eliminar el usuario.");
            request.getRequestDispatcher("perfil?idUsuario=" + idUsuario).forward(request, response);
        }
    }
}

