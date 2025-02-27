package it.univaq.testMiddleware.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DispositivoConSensoriDTO {
    private Long idDispositivo;
    private String nome;
    private String marca;
    private String modello;
    private String tipo;
    private String stato;
    private List<DatoSensoreDTO> sensori;
}
