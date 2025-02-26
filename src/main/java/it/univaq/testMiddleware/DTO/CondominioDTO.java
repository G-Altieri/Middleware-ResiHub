package it.univaq.testMiddleware.DTO;

import lombok.Data;

@Data
public class CondominioDTO {
    private Long idCondominio;
    private String nome;
    private String indirizzo;
    private UserDTO amministratore;
}