

package com.miltonlara.JuegodelAhorcado.service;

import com.miltonlara.JuegodelAhorcado.model.Palabra;
import com.miltonlara.JuegodelAhorcado.repository.PalabraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServiceImplements implements PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraServiceImplements(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra getPalabraById(Integer idPalabra) {
        return palabraRepository.findById(idPalabra)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + idPalabra));
    }

    @Override
    public Palabra savePalabra(Palabra palabra) {
        validarPalabraYPistas(palabra);

        if (palabraRepository.existsByPalabra(palabra.getPalabra())) {
            throw new RuntimeException("La palabra ya existe en el diccionario");
        }
        return palabraRepository.save(palabra);
    }

    @Override
    public Palabra updatePalabra(Integer idPalabra, Palabra palabra) {
        validarPalabraYPistas(palabra);

        Palabra existingPalabra = palabraRepository.findById(idPalabra)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + idPalabra));

        if (palabraRepository.existsByPalabraAndIdPalabraNot(palabra.getPalabra(), idPalabra)) {
            throw new RuntimeException("La palabra ya está en uso por otra entrada");
        }

        existingPalabra.setPalabra(palabra.getPalabra());
        existingPalabra.setPistaUno(palabra.getPistaUno());
        existingPalabra.setPistaDos(palabra.getPistaDos());
        existingPalabra.setPistaTres(palabra.getPistaTres());

        return palabraRepository.save(existingPalabra);
    }

    @Override
    public void deletePalabra(Integer idPalabra) {
        if (!palabraRepository.existsById(idPalabra)) {
            throw new RuntimeException("La palabra no existe");
        }
        palabraRepository.deleteById(idPalabra);
    }

    // Para que las pistas no puedan estar vacias tambien
    private void validarPalabraYPistas(Palabra palabra) {
        if (palabra == null) {
            throw new RuntimeException("La palabra no puede estar vacía");
        }
        if (palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            throw new RuntimeException("El campo 'palabra' no puede estar vacío");
        }
        if (palabra.getPistaUno() == null || palabra.getPistaUno().trim().isEmpty()) {
            throw new RuntimeException("La pista uno no puede estar vacía");
        }
        if (palabra.getPistaDos() == null || palabra.getPistaDos().trim().isEmpty()) {
            throw new RuntimeException("La pista dos no puede estar vacía");
        }
        if (palabra.getPistaTres() == null || palabra.getPistaTres().trim().isEmpty()) {
            throw new RuntimeException("La pista tres no puede estar vacía");
        }
    }
}
