package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@Tag(name = "Gestion Maintenance")
@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;

    @Operation(description = "Ajouter une nouvelle maintenance")
    @PostMapping
    public ResponseEntity<Maintenance> ajouter(@RequestBody Maintenance maintenance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.ajouterMaintenance(maintenance));
    }

    @Operation(description = "Récupérer toutes les maintenances")
    @GetMapping
    public List<Maintenance> recupererTous() {
        return maintenanceService.recupererToutesLesMaintenances();
    }

    @Operation(description = "Récupérer une maintenance par son identifiant")
    @GetMapping("/{id}")
    public Maintenance recuperer(@PathVariable Long id) {
        return maintenanceService.recupererMaintenance(id);
    }

    @Operation(description = "Modifier une maintenance existante")
    @PutMapping("/{id}")
    public Maintenance modifier(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return maintenanceService.modifierMaintenance(id, maintenance);
    }

    @Operation(description = "Supprimer une maintenance par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        maintenanceService.supprimerMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}