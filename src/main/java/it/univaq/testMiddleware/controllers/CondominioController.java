package it.univaq.testMiddleware.controllers;

import it.univaq.testMiddleware.DTO.CondominioDTO;
import it.univaq.testMiddleware.DTO.UserCondominiResponse;
import it.univaq.testMiddleware.DTO.UserDTO;
import it.univaq.testMiddleware.models.Condominio;
import it.univaq.testMiddleware.models.User;
import it.univaq.testMiddleware.repositories.CondominioRepository;
import it.univaq.testMiddleware.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/condomini")
public class CondominioController {

    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/my")
    public ResponseEntity<?> getCondominiForAuthenticatedUser() {
        // Ottieni il nome utente dal SecurityContext (impostato dal filtro JWT)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Carica l'utente dal database
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("Utente non trovato");
        }

        // Recupera i condomini gestiti dall'utente (amministratore)
        List<Condominio> condomini = condominioRepository.findByAmministratore(user);

        // Mappa i condomini in DTO (senza includere l'amministratore)
        List<CondominioDTO> condominiDTO = condomini.stream().map(cond -> {
            CondominioDTO dto = new CondominioDTO();
            dto.setIdCondominio(cond.getIdCondominio());
            dto.setNome(cond.getNome());
            dto.setIndirizzo(cond.getIndirizzo());
            return dto;
        }).collect(Collectors.toList());

        // Mappa l'utente in UserDTO (senza password)
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        // Crea la risposta finale
        UserCondominiResponse response = new UserCondominiResponse();
        response.setUser(userDTO);
        response.setCondomini(condominiDTO);

        return ResponseEntity.ok(response);
    }
}
