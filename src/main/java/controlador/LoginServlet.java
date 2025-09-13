/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import dao.UsuarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import modelo.usuario;

/**
 *
 * @author santi
 */


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDao usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nickname = request.getParameter("nickname");
        String contrasena = request.getParameter("contrasena"); 

        try {
            // OJO: método debe devolver un objeto modelo.usuario
            usuario usuarioObj = usuarioDAO.iniciarSesion(nickname, contrasena);

            if (usuarioObj != null) {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuarioIn", usuarioObj);
                response.sendRedirect(request.getContextPath() + "/vista/dashboard.jsp");
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("/inicioUsuario.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar la autenticación: " + e.getMessage());
            request.getRequestDispatcher("/inicioUsuario.jsp").forward(request, response);
        }
    }
}



