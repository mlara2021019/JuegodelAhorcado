package com.miltonlara.JuegodelAhorcado.repository;

import com.miltonlara.JuegodelAhorcado.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {

    // Método para verificar si una palabra ya existe
    boolean existsByPalabra(String palabra);

    // Método para verificar si una palabra existe con otro ID diferente (para actualizaciones)
    boolean existsByPalabraAndIdPalabraNot(String palabra, Integer idPalabra);

}
