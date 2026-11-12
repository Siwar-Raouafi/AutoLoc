package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@Tag(name = "Gestion Reservation")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationRestController {

    private final IReservationService reservationService;

    @Operation(description = "Ajouter une nouvelle réservation")
    @PostMapping
    public ResponseEntity<Reservation> ajouter(@RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.ajouterReservation(reservation));
    }

    @Operation(description = "Récupérer toutes les réservations")
    @GetMapping
    public List<Reservation> recupererTous() {
        return reservationService.recupererToutesLesReservations();
    }

    @Operation(description = "Récupérer une réservation par son identifiant")
    @GetMapping("/{id}")
    public Reservation recuperer(@PathVariable Long id) {
        return reservationService.recupererReservation(id);
    }

    @Operation(description = "Modifier une réservation existante")
    @PutMapping("/{id}")
    public Reservation modifier(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.modifierReservation(id, reservation);
    }

    @Operation(description = "Supprimer une réservation par son identifiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        reservationService.supprimerReservation(id);
        return ResponseEntity.noContent().build();
    }
}