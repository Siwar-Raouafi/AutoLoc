package tn.esprit.autoloc.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<Vehicule> ajouter(@RequestBody Vehicule vehicule) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeService.ajouterVehicule(vehicule));
    }

    @GetMapping
    public List<Vehicule> recupererTous() {
        return vehiculeService.recupererTousLesVehicules();
    }

    @GetMapping("/{id}")
    public Vehicule recuperer(@PathVariable Long id) {
        return vehiculeService.recupererVehicule(id);
    }

    @PutMapping("/{id}")
    public Vehicule modifier(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.modifierVehicule(id, vehicule);
    }

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