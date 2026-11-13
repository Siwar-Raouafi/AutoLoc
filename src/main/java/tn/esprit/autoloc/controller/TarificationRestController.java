package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.tarification.TarificationService;

@Tag(name = "Gestion Tarification")
@RestController
@RequestMapping("/api/tarifs")
@RequiredArgsConstructor
public class TarificationRestController {

    private final TarificationService tarificationService;

    @Operation(description = "Calculer le tarif selon la stratégie (STANDARD, WEEKEND, LONGUE_DUREE, ENTREPRISE)")
    @GetMapping("/calculer")
    public double calculer(
            @RequestParam String typeTarif,
            @RequestParam double tarifJournalier,
            @RequestParam int jours) {
        return tarificationService.calculerTarif(typeTarif, tarifJournalier, jours);
    }
}