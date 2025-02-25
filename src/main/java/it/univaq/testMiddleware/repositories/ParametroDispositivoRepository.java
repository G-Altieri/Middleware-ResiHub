package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.ParametroDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroDispositivoRepository extends JpaRepository<ParametroDispositivo, Long> {
    // query personalizzate, se necessarie
}
