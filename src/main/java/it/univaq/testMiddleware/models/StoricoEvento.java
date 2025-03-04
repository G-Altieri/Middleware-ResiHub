package it.univaq.testMiddleware.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "storico_eventi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoricoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    private String descrizione;

    private Boolean confermaLettura;

    @Column(name = "timestamp")
    private Instant timestamp;

    // Relazione con Dispositivo
    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;
}
