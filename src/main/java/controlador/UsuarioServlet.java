/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;


import dao.UsuarioDao;
import modelo.usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author santi
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDao usuarioDAO;

    @Override
    public void init() throws ServletException {
        try {
            usuarioDAO = new UsuarioDao(); // aqui el constructor prepara la conexion
        } catch (Exception e) {
            // si falla pues se cae todo JAJAJ
            throw new ServletException("Error inicializando UsuarioDao", e);
        }
    }

    /**
     * GET: redirige al formulario 
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se va de una al registro la ruta absoluta
        request.getRequestDispatcher("/vista/registroUsuario.jsp").forward(request, response);
    }

    /**
     * POST: procesa el envío del formulario y guarda el usuario
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // importante para evitar problemas con acentos/caracteres especiales
        request.setCharacterEncoding("UTF-8");

        try {
            String nombre = request.getParameter("nombre");
            String nickname = request.getParameter("nickname");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            String rol = request.getParameter("rol");

            // se validan los parametros aquii

            usuario usuario = new usuario();
            usuario.setNombre(nombre);
            usuario.setNickname(nickname);
            usuario.setCorreo(correo);
            usuario.setContraseña(contrasena);
            usuario.setRol(rol);

            boolean exito = usuarioDAO.guardarUsuario(usuario);

            if (exito) {
                request.setAttribute("mensaje", "Usuario registrado correctamente");
            } else {
                request.setAttribute("mensaje", "Error al registrar el usuario");
            }
        } catch (Exception e) {
            // por si hay error
            getServletContext().log("Error en UsuarioServlet doPost: " + e.getMessage(), e);
            request.setAttribute("mensaje", "Error al procesar la solicitud: " + e.getMessage());
        }

        // redirige al resultado cuando todo se hizo correctamente
        request.getRequestDispatcher("/vista/resultado.jsp").forward(request, response);
    }
}