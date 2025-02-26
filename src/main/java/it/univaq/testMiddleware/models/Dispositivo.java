package it.univaq.testMiddleware.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "dispositivi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"parametriDispositivo", "datiSensori", "storicoEventi"})
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Long idDispositivo;

    private String nome;
    private String marca;
    private String modello;
    private String tipo;
    private String stato;

    // Relazione con Condominio (FK)
    @ManyToOne
    @JoinColumn(name = "condominio_id")
    private Condominio condominio;

    // 1-N con ParametroDispositivo
    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    private List<ParametroDispositivo> parametriDispositivo;


    // 1-N con StoricoEvento
    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    private List<StoricoEvento> storicoEventi;
}
