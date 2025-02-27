package it.univaq.testMiddleware.DTO;

import lombok.Data;

import java.time.Instant;

@Data
public class DatoSensoreDTO {
    private Long idSensore;
    private String valore;
    private Instant timestamp;
}
