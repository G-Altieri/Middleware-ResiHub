package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.DatoSensore;
import it.univaq.testMiddleware.models.ParametroDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface DatoSensoreRepository extends JpaRepository<DatoSensore, Long> {
    List<DatoSensore> findByParametro_Dispositivo_IdDispositivoAndTimestampBetween(
            Long idDispositivo, Instant start, Instant end);

    List<DatoSensore> findByParametroIn(List<ParametroDispositivo> parametri);

    // Nuovo metodo: restituisce i dati sensore per un parametro in un intervallo di tempo
    List<DatoSensore> findByParametroAndTimestampBetween(ParametroDispositivo parametro, Instant start, Instant end);

    // Nuovo metodo: restituisce tutti i dati sensore per un dato parametro
    List<DatoSensore> findByParametro(ParametroDispositivo parametro);

    // Metodo per recuperare l'ultimo dato sensore per un dato parametro
    DatoSensore findFirstByParametroOrderByTimestampDesc(ParametroDispositivo parametro);
}
