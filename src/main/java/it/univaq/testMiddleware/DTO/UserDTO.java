package it.univaq.testMiddleware.DTO;

import lombok.Data;
import java.util.Date;

// DTO per l'utente (amministratore)
@Data
public class UserDTO {
    private Long id;
    private String username;

    // Nuovi campi per il profilo
    private String nome;
    private String cognome;
    private String email;
    private String numeroDiTelefono;
    private Date dataNascita;

    // Campo calcolato: numero di condomini gestiti
    private int numeroCondominiGestiti;
}
