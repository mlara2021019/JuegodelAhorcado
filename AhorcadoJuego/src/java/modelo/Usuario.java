package modelo;

public class Usuario {
    private int codigo_Usuario;
    private String usuario;
    private String contrasena;

    // Constructor vacío
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(int codigo_Usuario, String usuario, String contrasena) {
        this.codigo_Usuario = codigo_Usuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getCodigo_Usuario() {
        return codigo_Usuario;
    }

    public void setCodigo_Usuario(int codigo_Usuario) {
        this.codigo_Usuario = codigo_Usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Método toString (sin mostrar contraseña)
    @Override
    public String toString() {
        return "Usuario{" + "codigo_Usuario=" + codigo_Usuario + ", usuario=" + usuario + ", contrasena=****}";
    }
}
