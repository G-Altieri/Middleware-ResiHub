package it.univaq.testMiddleware.repositories;

import it.univaq.testMiddleware.models.Condominio;
import it.univaq.testMiddleware.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    List<Condominio> findByAmministratore(User amministratore);
}
