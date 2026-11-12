package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Gestion Paiement")
@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementRestController {

    private final IPaiementService paiementService;

    @Operation(description = "Ajouter un nouveau paiement")
    @PostMapping
    public ResponseEntity<Paiement> ajouter(@RequestBody Paiement paiement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.ajouterPaiement(paiement));
    }

    @Operation(description = "Récupérer tous les paiements")
    @GetMapping
    public List<Paiement> recupererTous() {
        return paiementService.recupererTousLesPaiements();
    }

    @Operation(description = "Récupérer un paiement par son identifiant")
    @GetMapping("/{id}")
    public Paiement recuperer(@PathVariable Long id) {
        return paiementService.recupererPaiement(id);
    }

    @Operation(description = "Modifier un paiement existant")
    @PutMapping("/{id}")
    public Paiement modifier(@PathVariable Long id, @RequestBody Paiement paiement) {
        return paiementService.modifierPaiement(id, paiement);
    }

    @Operation(description = "Supprimer un paiement par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        paiementService.supprimerPaiement(id);
        return ResponseEntity.noContent().build();
    }
}