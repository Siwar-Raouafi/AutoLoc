package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Tag(name = "Gestion Contrat")
@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratRestController {

    private final IContratService contratService;

    @Operation(description = "Ajouter un nouveau contrat")
    @PostMapping
    public ResponseEntity<Contrat> ajouter(@RequestBody Contrat contrat) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.ajouterContrat(contrat));
    }

    @Operation(description = "Récupérer tous les contrats")
    @GetMapping
    public List<Contrat> recupererTous() {
        return contratService.recupererTousLesContrats();
    }

    @Operation(description = "Récupérer un contrat par son identifiant")
    @GetMapping("/{id}")
    public Contrat recuperer(@PathVariable Long id) {
        return contratService.recupererContrat(id);
    }

    @Operation(description = "Modifier un contrat existant")
    @PutMapping("/{id}")
    public Contrat modifier(@PathVariable Long id, @RequestBody Contrat contrat) {
        return contratService.modifierContrat(id, contrat);
    }

    @Operation(description = "Supprimer un contrat par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        contratService.supprimerContrat(id);
        return ResponseEntity.noContent().build();
    }
}