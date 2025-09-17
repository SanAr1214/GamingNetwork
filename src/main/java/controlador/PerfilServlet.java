/*
 * Servlet que maneja todo lo del perfil de usuario
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

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

    private UsuarioDao usuarioDao = new UsuarioDao();

    // Cuando se entra por GET (mostrar perfil)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        usuario u = usuarioDao.getById(idUsuario);

        if (u != null) {
            // mando el usuario a la vista perfil.jsp
            request.setAttribute("usuario", u);
            request.getRequestDispatcher("/vista/perfil.jsp").forward(request, response);
        } else {
            // si no existe me manda de nuevo al inicio
            response.sendRedirect(request.getContextPath() + "/inicioUsuario.jsp");
        }
    }

    // Cuando se entra por POST (actualizar perfil)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String nombre = request.getParameter("nombre");
        String nickname = request.getParameter("nickname");
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");

        // armo el objeto usuario con los datos del form
        usuario u = new usuario();
        u.setIdUsuario(idUsuario);
        u.setNombre(nombre);
        u.setNickname(nickname);
        u.setCorreo(correo);
        if (contraseña != null && !contraseña.isEmpty()) {
            u.setContraseña(contraseña); // solo actualizo si la escriben
        }

        boolean actualizado = usuarioDao.actualizarUsuario(u);

        if (actualizado) {
            // si actualizo, recargo el perfil
            response.sendRedirect("perfil?idUsuario=" + idUsuario);
        } else {
            // si no, muestro error y vuelvo al perfil.jsp
            request.setAttribute("error", "No se pudo actualizar el perfil.");
            request.setAttribute("usuario", u);
            request.getRequestDispatcher("/vista/perfil.jsp").forward(request, response);
        }
    }
}
