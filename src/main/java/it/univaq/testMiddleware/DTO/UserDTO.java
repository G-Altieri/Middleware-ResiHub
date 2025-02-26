package it.univaq.testMiddleware.DTO;

import lombok.Data;

// DTO per l'utente (amministratore)
@Data
public class UserDTO {
    private Long id;
    private String username;
    // Per sicurezza potresti omettere la password
}