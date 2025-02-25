package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.DatoSensore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoSensoreRepository extends JpaRepository<DatoSensore, Long> {
    // query personalizzate, se necessarie
}
