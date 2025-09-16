package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Palabras;
import modelo.PalabrasDAO;
import modelo.Usuario;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        // ✅ Manejo de menú principal después del login
        if ("MenuPrincipal".equals(menu)) {
            Usuario usuario = (Usuario) request.getAttribute("usuario");

            if (usuario != null) {
                // Guardar en sesión para usar en todas las páginas
                request.getSession().setAttribute("usuario", usuario);
                request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
            } else {
                // Si no viene usuario, vuelve al login
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            return;
        }

        // ✅ Lógica para obtener palabra aleatoria en JSON
        if ("obtenerPalabra".equals(accion)) {
            PalabrasDAO dao = new PalabrasDAO();
            Palabras palabra = dao.obtenerPalabraAleatoria();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (palabra != null) {
                Gson gson = new Gson();
                response.getWriter().write(gson.toJson(palabra));
            } else {
                response.getWriter().write("{\"error\":\"No se pudo obtener la palabra\"}");
            }
            return;
        }

        // ✅ Si no hay parámetros, redirige al login por defecto
        if (menu == null && accion == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
