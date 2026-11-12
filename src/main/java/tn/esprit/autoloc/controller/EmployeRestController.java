package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Gestion Employe")
@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeRestController {

    private final IEmployeService employeService;

    @Operation(description = "Ajouter un nouvel employé")
    @PostMapping
    public ResponseEntity<Employe> ajouter(@RequestBody Employe employe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeService.ajouterEmploye(employe));
    }

    @Operation(description = "Récupérer tous les employés")
    @GetMapping
    public List<Employe> recupererTous() {
        return employeService.recupererTousLesEmployes();
    }

    @Operation(description = "Récupérer un employé par son identifiant")
    @GetMapping("/{id}")
    public Employe recuperer(@PathVariable Long id) {
        return employeService.recupererEmploye(id);
    }

    @Operation(description = "Modifier un employé existant")
    @PutMapping("/{id}")
    public Employe modifier(@PathVariable Long id, @RequestBody Employe employe) {
        return employeService.modifierEmploye(id, employe);
    }

    @Operation(description = "Supprimer un employé par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        employeService.supprimerEmploye(id);
        return ResponseEntity.noContent().build();
    }
}