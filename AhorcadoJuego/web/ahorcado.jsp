<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Juego del Ahorcado de Mario</title>
    <link rel="stylesheet" href="css/css.css">
    <script src="js/script.js" defer></script>
</head>
<body>
    <h1>Juego del Ahorcado de Mario</h1>

    <div class="barra-hud">
        <div class="botones-hud">
            <button id="btnPausa" onclick="juegoPausado ? reanudarJuego() : pausarJuego()">Pausa</button>
            <button id="btnReiniciar" onclick="iniciarJuego()">Reiniciar</button>
            <button id="btnSalir" onclick="window.location.href='index.jsp'">Salir</button>
        </div>
        <div id="cronometro">00:00</div>
    </div>


    <div class="juego">
        <div class="ahorcado-container">
            <img id="imagen" src="img/mario1.jpg" alt="Ahorcado">
        </div>
        <div id="palabra" class="palabra"></div>
        <div id="pistas" class="pistas"></div>
        <div id="intentos"></div>
        <div id="teclado" class="teclado"></div>
    </div>

    
<div id="modal" class="modal">
    <div class="modal-content">
        <div id="modal-texto"></div> 
        <button onclick="cerrarModal()">Aceptar</button>
    </div>
</div>

</body>
</html>
