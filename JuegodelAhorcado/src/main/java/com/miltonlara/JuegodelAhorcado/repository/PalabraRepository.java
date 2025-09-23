package com.miltonlara.JuegodelAhorcado.repository;

import com.miltonlara.JuegodelAhorcado.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {

    //para ver si una palabra ya existe
    boolean existsByPalabra(String palabra);

    //para ver si una palabra existe con otro ID diferente es para actualizaciones
    boolean existsByPalabraAndIdPalabraNot(String palabra, Integer idPalabra);

}
