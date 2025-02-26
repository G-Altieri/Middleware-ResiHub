package it.univaq.testMiddleware.DTO;

import lombok.Data;

import java.util.List;

// DTO per il parametro
@Data
public class ParametroDTO {
    private Long idParametro;
    private String nome;
    private String tipologia;
    private String unitaMisura;
    private Double valMin;
    private Double valMax;
    private List<SensorValueDTO> valori;  // I dati sensore per questo parametro
}