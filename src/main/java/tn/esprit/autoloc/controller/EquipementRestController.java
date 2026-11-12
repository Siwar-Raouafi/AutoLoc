package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Gestion Equipement")
@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementRestController {

    private final IEquipementService equipementService;

    @Operation(description = "Ajouter un nouvel équipement")
    @PostMapping
    public ResponseEntity<Equipement> ajouter(@RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.ajouterEquipement(equipement));
    }

    @Operation(description = "Récupérer tous les équipements")
    @GetMapping
    public List<Equipement> recupererTous() {
        return equipementService.recupererTousLesEquipements();
    }

    @Operation(description = "Récupérer un équipement par son identifiant")
    @GetMapping("/{id}")
    public Equipement recuperer(@PathVariable Long id) {
        return equipementService.recupererEquipement(id);
    }

    @Operation(description = "Modifier un équipement existant")
    @PutMapping("/{id}")
    public Equipement modifier(@PathVariable Long id, @RequestBody Equipement equipement) {
        return equipementService.modifierEquipement(id, equipement);
    }

    @Operation(description = "Supprimer un équipement par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        equipementService.supprimerEquipement(id);
        return ResponseEntity.noContent().build();
    }
}