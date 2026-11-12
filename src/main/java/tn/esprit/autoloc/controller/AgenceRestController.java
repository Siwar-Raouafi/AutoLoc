package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Gestion Agence")
@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceRestController {

    private final IAgenceService agenceService;

    @Operation(description = "Ajouter une nouvelle agence")
    @PostMapping
    public ResponseEntity<Agence> ajouter(@RequestBody Agence agence) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.ajouterAgence(agence));
    }

    @Operation(description = "Récupérer toutes les agences")
    @GetMapping
    public List<Agence> recupererTous() {
        return agenceService.recupererToutesLesAgences();
    }

    @Operation(description = "Récupérer une agence par son identifiant")
    @GetMapping("/{id}")
    public Agence recuperer(@PathVariable Long id) {
        return agenceService.recupererAgence(id);
    }

    @Operation(description = "Modifier une agence existante")
    @PutMapping("/{id}")
    public Agence modifier(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.modifierAgence(id, agence);
    }

    @Operation(description = "Supprimer une agence par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        agenceService.supprimerAgence(id);
        return ResponseEntity.noContent().build();
    }
}