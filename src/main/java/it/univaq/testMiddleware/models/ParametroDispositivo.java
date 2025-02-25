package it.univaq.testMiddleware.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parametri_dispositivi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametroDispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private Long idParametro;

    @Column(name = "unita_misura")
    private String unitaMisura;

    // Relazione con Dispositivo
    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivo;
}
