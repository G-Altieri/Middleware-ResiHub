package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    // query personalizzate, se necessarie
}
