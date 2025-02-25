package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    // query personalizzate, se necessarie
}
