package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Tag(name = "Gestion Vehicule")
@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @Operation(description = "Ajouter un nouveau véhicule dans la base de données")
    @PostMapping
    public ResponseEntity<Vehicule> ajouter(@RequestBody Vehicule vehicule) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeService.ajouterVehicule(vehicule));
    }

    @Operation(description = "Récupérer tous les véhicules de la base de données")
    @GetMapping
    public List<Vehicule> recupererTous() {
        return vehiculeService.recupererTousLesVehicules();
    }

    @Operation(description = "Récupérer un véhicule par son identifiant")
    @GetMapping("/{id}")
    public Vehicule recuperer(@PathVariable Long id) {
        return vehiculeService.recupererVehicule(id);
    }

    @Operation(description = "Modifier un véhicule existant")
    @PutMapping("/{id}")
    public Vehicule modifier(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.modifierVehicule(id, vehicule);
    }

    @Operation(description = "Supprimer un véhicule par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}