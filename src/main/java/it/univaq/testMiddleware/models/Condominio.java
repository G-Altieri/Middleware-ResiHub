package it.univaq.testMiddleware.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "condomini")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "dispositivi")
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_condominio")
    private Long idCondominio;

    private String nome;
    private String indirizzo;

    // Relazione con User (l'amministratore)
    @ManyToOne
    @JoinColumn(name = "amministratore_id") // FK verso "utenti"
    @JsonIgnoreProperties("tokens")
    private User amministratore;

    // Relazione 1-N con Dispositivo: escludo la serializzazione per evitare problemi di lazy loading
    @JsonIgnore
    @OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL)
    private List<Dispositivo> dispositivi;
}
