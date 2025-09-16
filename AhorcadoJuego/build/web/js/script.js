
let imagenesPalabras = {
    "ACUARELA": "img/Acuarela.jpg",
    "VOLCANICO": "img/Volcan.jpg",
    "RELAMPAGO": "img/Relampago.jpg",
    "TELEFONO": "img/Telefono.jpg",
    "MANUSCRITO": "img/Manuscrito.jpg"
};

let palabraSecreta = "";
let intentos = 0;
let maxIntentos = 6;
let pistasActuales = [];
let juegoPausado = false;


let tiempoLimite = 10 * 60;  
let tiempoRestante = tiempoLimite;
let intervaloTiempo;


window.onload = () => iniciarJuego();

async function iniciarJuego() {
    intentos = 0;
    tiempoRestante = tiempoLimite;
    juegoPausado = false;

    mostrarTiempo();

    try {
        const respuesta = await fetch('./Controlador?accion=obtenerPalabra');
        if (!respuesta.ok) throw new Error("Error al obtener la palabra del servidor");
        const palabraData = await respuesta.json();

        palabraSecreta = palabraData.palabra.toUpperCase();
        pistasActuales = [palabraData.pista_1, palabraData.pista_2, palabraData.pista_3];

        generarPistas(pistasActuales);
        mostrarPalabra();
        generarTeclado();
        actualizarImagen();

        if (intervaloTiempo) clearInterval(intervaloTiempo);
        iniciarCronometro();

    } catch (error) {
        console.error("Error en la petición:", error);
        document.getElementById("palabra").textContent = "No se pudo iniciar el juego.";
    }
}

// Temporizador 
function mostrarTiempo() {
    let minutos = Math.floor(tiempoRestante / 60);
    let segundos = tiempoRestante % 60;
    document.getElementById("cronometro").textContent = `Tiempo restante: ${minutos.toString().padStart(2,"0")}:${segundos.toString().padStart(2,"0")}`;
}

function iniciarCronometro() {
    intervaloTiempo = setInterval(() => {
        if (!juegoPausado) {
            tiempoRestante--;
            mostrarTiempo();
            if (tiempoRestante <= 0) {
                clearInterval(intervaloTiempo);
                mostrarModalConImagen("¡Se acabó el tiempo! La palabra era", palabraSecreta);
            }
        }
    }, 1000);
}

function pausarJuego() { juegoPausado = true; }
function reanudarJuego() { juegoPausado = false; }

function generarPistas(pistas) {
    let pistasContainer = document.getElementById("pistas");
    pistasContainer.innerHTML = "";
    pistas.forEach((pista, i) => {
        let p = document.createElement("p");
        p.textContent = `Pista ${i + 1}: ${pista}`;
        pistasContainer.appendChild(p);
    });
}

function mostrarPalabra() {
    let div = document.getElementById("palabra");
    div.innerHTML = "";
    for (let letra of palabraSecreta) {
        let span = document.createElement("span");
        span.textContent = "_";
        span.classList.add("letra");
        div.appendChild(span);
    }
}

function generarTeclado() {
    let teclado = document.getElementById("teclado");
    teclado.innerHTML = "";
    let letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    for (let letra of letras) {
        let btn = document.createElement("button");
        btn.textContent = letra;
        btn.onclick = () => verificarLetra(letra, btn);
        teclado.appendChild(btn);
    }
}

function verificarLetra(letra, boton) {
    if (juegoPausado) return;

    boton.disabled = true;
    let letrasDOM = document.querySelectorAll(".letra");
    let acierto = false;

    for (let i = 0; i < palabraSecreta.length; i++) {
        if (palabraSecreta[i] === letra) {
            letrasDOM[i].textContent = letra;
            acierto = true;
        }
    }

    if (!acierto) {
        intentos++;
        actualizarImagen();
    }

    revisarEstado();
}

function actualizarImagen() {
    let ahorcado = intentos + 1;
    if (ahorcado > 7) ahorcado = 7;
    document.getElementById("imagen").src = `img/mario${ahorcado}.jpg`;
}

function revisarEstado() {
    let letrasDOM = document.querySelectorAll(".letra");
    let palabraActual = Array.from(letrasDOM).map(span => span.textContent).join("");

    if (palabraActual === palabraSecreta) {
        mostrarModalConImagen("¡Ganaste! La palabra era", palabraSecreta);
    } else if (intentos >= maxIntentos) {
        mostrarModalConImagen("¡Perdiste! La palabra era", palabraSecreta);
    }
}

function mostrarModalConImagen(mensaje, palabra) {
    const modal = document.getElementById("modal");
    const modalTexto = document.getElementById("modal-texto");

    modalTexto.innerHTML = `<p>${mensaje} "${palabra}"</p>`;

    const imgPalabra = document.createElement("img");
    imgPalabra.src = imagenesPalabras[palabra.toUpperCase()] || "img/default.jpg";
    imgPalabra.alt = palabra;
    imgPalabra.style.width = "200px";
    imgPalabra.style.marginTop = "10px";

    modalTexto.appendChild(imgPalabra);
    modal.style.display = "block";

    document.querySelectorAll("#teclado button").forEach(btn => btn.disabled = true);
    clearInterval(intervaloTiempo);
}

function cerrarModal() {
    document.getElementById("modal").style.display = "none";
    iniciarJuego();
}
