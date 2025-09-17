/*
 * este servlet se encarga de eliminar un usuario del sistema
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

    // aqui llamo el dao para poder usar la logica de eliminar
    private UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // agarro el id del usuario que viene del formulario
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        // intento eliminar el usuario con ese id
        boolean eliminado = usuarioDao.eliminarUsuario(idUsuario);

        // si se logro eliminar redirijo a la pagina de inicio
        if (eliminado) {
            response.sendRedirect("inicioUsuario.jsp");
        } else {
            // si no se pudo eliminar, muestro un error y vuelvo al perfil
            request.setAttribute("error", "No se pudo eliminar el usuario.");
            request.getRequestDispatcher("perfil?idUsuario=" + idUsuario).forward(request, response);
        }
    }
}
