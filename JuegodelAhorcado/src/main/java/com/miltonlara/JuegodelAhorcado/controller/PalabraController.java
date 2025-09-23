
package com.miltonlara.JuegodelAhorcado.controller;

import com.miltonlara.JuegodelAhorcado.model.Palabra;
import com.miltonlara.JuegodelAhorcado.service.PalabraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/palabras")
public class PalabraController {

    private final PalabraService palabraService;

    public PalabraController(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPalabras() {
        try {
            List<Palabra> palabras = palabraService.getAllPalabras();
            return ResponseEntity.ok(palabras);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPalabraById(@PathVariable("id") Integer idPalabra) {
        try {
            Palabra palabra = palabraService.getPalabraById(idPalabra);
            return ResponseEntity.ok(palabra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPalabra(@RequestBody Palabra palabra) {
        try {
            Palabra newPalabra = palabraService.savePalabra(palabra);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPalabra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePalabra(@PathVariable("id") Integer idPalabra, @RequestBody Palabra palabra) {
        try {
            Palabra updatedPalabra = palabraService.updatePalabra(idPalabra, palabra);
            return ResponseEntity.ok(updatedPalabra);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePalabra(@PathVariable("id") Integer idPalabra) {
        try {
            palabraService.deletePalabra(idPalabra);
            return ResponseEntity.ok("Se eliminó correctamente la palabra");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}