package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.SessioneRicarica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessioneRicaricaRepository extends JpaRepository<SessioneRicarica, Long> {
    // Qui puoi definire query personalizzate se necessario
}
