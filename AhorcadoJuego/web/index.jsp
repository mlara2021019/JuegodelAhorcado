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
            <form class="login-form" action="ahorcado.jsp" method="get" novalidate>
                <h1>Login</h1>
                <div class="field">
                    <input type="text" id="usuario" name="usuario" required placeholder=" ">
                    <label for="usuario">Usuario</label>
                    <div class="error-msg">Ingresa tu usuario</div>
                </div>
                <div class="field">
                    <input type="password" id="contrasena" name="contrasena" required placeholder=" ">
                    <label for="contrasena">Contraseña</label>
                    <div class="error-msg">Ingresa tu contraseña</div>
                </div>
                <button type="submit">Aceptar</button>
            </form>
        </div>
    </body>

</html>
