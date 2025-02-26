package it.univaq.testMiddleware.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")  // Per mappare la tabella "utenti" del DB
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tokens", "condominiGestiti"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente") // Se vuoi rispettare il nome della PK "id_utente" nel DB
    private Long id;

    private String username;

    @Column(nullable = false)
    private String password; // Deve essere criptata con BCrypt!

    // Lista dei token associati all'utente (già presente)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore  // Aggiungi questa annotazione per non serializzare tokens
    private List<Token> tokens;

    // Relazione bidirezionale con Condominio (opzionale, se vuoi vedere i condomini gestiti)
    @OneToMany(mappedBy = "amministratore", cascade = CascadeType.ALL)
    @JsonIgnore  // Aggiungi questa annotazione per non serializzare tokens
    private List<Condominio> condominiGestiti;
}
