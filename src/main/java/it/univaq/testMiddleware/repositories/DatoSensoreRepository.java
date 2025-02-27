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
}