package it.univaq.testMiddleware.controllers;

import it.univaq.testMiddleware.DTO.*;
import it.univaq.testMiddleware.models.*;
import it.univaq.testMiddleware.repositories.CondominioRepository;
import it.univaq.testMiddleware.repositories.DatoSensoreRepository;
import it.univaq.testMiddleware.repositories.DispositivoRepository;
import it.univaq.testMiddleware.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/condomini")
public class CondominioController {

    @Autowired
    private CondominioRepository condominioRepository;
    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DatoSensoreRepository datoSensoreRepository;


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


    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> getDispositiviConSensori(@PathVariable("id") Long id) {
        Optional<Condominio> condOptional = condominioRepository.findById(id);
        if (!condOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Condominio non trovato");
        }
        Condominio condominio = condOptional.get();
        System.out.println("CONDOMINIO TROVATO");

        // Recupera i dispositivi associati al condominio
        List<Dispositivo> dispositivi = dispositivoRepository.findByCondominio(condominio);

        // Mappa ogni dispositivo in DispositivoConSensoriDTO
        List<DispositivoConSensoriDTO> dispositiviConSensoriDTO = dispositivi.stream().map(d -> {
            DispositivoConSensoriDTO dto = new DispositivoConSensoriDTO();
            dto.setIdDispositivo(d.getIdDispositivo());
            dto.setNome(d.getNome());
            dto.setMarca(d.getMarca());
            dto.setModello(d.getModello());
            dto.setTipo(d.getTipo());
            dto.setStato(d.getStato());

            // Recupera i parametri relativi a questo dispositivo
            List<ParametroDispositivo> parametri = d.getParametriDispositivo();
            List<DatoSensoreDTO> sensoriDTO = Collections.emptyList();
            if (parametri != null && !parametri.isEmpty()) {
                // Per ogni dispositivo si possono recuperare i sensori associati ai suoi parametri
                List<DatoSensore> sensori = datoSensoreRepository.findByParametroIn(parametri);
                sensoriDTO = sensori.stream().map(s -> {
                    DatoSensoreDTO sensorDto = new DatoSensoreDTO();
                    sensorDto.setIdSensore(s.getIdSensore());
                    sensorDto.setValore(s.getValore());
                    sensorDto.setTimestamp(s.getTimestamp());
                    return sensorDto;
                }).collect(Collectors.toList());
            }
            dto.setSensori(sensoriDTO);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dispositiviConSensoriDTO);
    }

}