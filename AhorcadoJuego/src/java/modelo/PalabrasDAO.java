package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PalabrasDAO {

    public Palabras obtenerPalabraAleatoria() {
        Palabras palabra = null;
        String sql = "{CALL  sp_obtener_palabras_aleatorias()}";
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            Conexion cn = new Conexion();
            con = cn.Conexion(); 
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();

            if (rs.next()) {
                palabra = new Palabras();
                palabra.setCodigo_Palabra(rs.getInt("codigo_Palabra"));
                palabra.setPalabra(rs.getString("palabra"));
                palabra.setPista_1(rs.getString("pista_1"));
                palabra.setPista_2(rs.getString("pista_2"));
                palabra.setPista_3(rs.getString("pista_3"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener palabra: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return palabra;
    }
}
