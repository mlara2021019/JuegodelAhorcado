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

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        // Acción para obtener una palabra aleatoria
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

        // Si no hay ninguna acción, redirige al index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
