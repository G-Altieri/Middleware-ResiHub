package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.StoricoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoricoEventoRepository extends JpaRepository<StoricoEvento, Long> {
    // query personalizzate, se necessarie
}
