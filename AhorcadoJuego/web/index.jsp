<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Juego del Ahorcado Mario</title>
        <link rel="stylesheet" href="css/login.css">

    </head>
    <body>
        <div class="form-box">
            <form class="login-form" action="Validar" method="POST" novalidate>
                <h1>Login</h1>

                //mensaje de error
                <% if (request.getAttribute("error") != null) {%>
                <div class="error-box">
                    <%= request.getAttribute("error")%>
                </div>
                <% }%>

                <div class="field">
                    <input type="text" id="usuario" name="txtUsuario" required placeholder=" ">
                    <label for="usuario">Usuario</label>
                    <div class="error-msg">Ingresa tu usuario</div>
                </div>

                <div class="field">
                    <input type="password" id="contrasena" name="txtPassword" required placeholder=" " minlength="4">
                    <label for="contrasena">Contraseña</label>
                    <div class="error-msg">Ingresa tu contraseña</div>
                </div>

                <button type="submit" name="accion" value="Ingresar">Aceptar</button>
            </form>
        </div>
    </body>
</html>
