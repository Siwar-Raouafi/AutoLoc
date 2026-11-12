package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Gestion Client")
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientRestController {

    private final IClientService clientService;

    @Operation(description = "Ajouter un nouveau client")
    @PostMapping
    public ResponseEntity<Client> ajouter(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.ajouterClient(client));
    }

    @Operation(description = "Récupérer tous les clients")
    @GetMapping
    public List<Client> recupererTous() {
        return clientService.recupererTousLesClients();
    }

    @Operation(description = "Récupérer un client par son identifiant")
    @GetMapping("/{id}")
    public Client recuperer(@PathVariable Long id) {
        return clientService.recupererClient(id);
    }

    @Operation(description = "Modifier un client existant")
    @PutMapping("/{id}")
    public Client modifier(@PathVariable Long id, @RequestBody Client client) {
        return clientService.modifierClient(id, client);
    }

    @Operation(description = "Supprimer un client par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }
}