package it.univaq.testMiddleware.DTO;

import lombok.Data;

@Data
public class CondominioDTO {
    private Long idCondominio;
    private String nome;
    private String indirizzo;
    private UserDTO amministratore;


    private Integer annoCostruzione;
    private String classeEnergetica;
    private Integer numeroPiani;
    private String regolamenti;
    private Double superficie;
    private Integer unitaAbitative;
}
