package tn.esprit.autoloc.service.tarification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;

    public double calculerTarif(String typeTarif, double tarifJournalier, int jours) {
        if (tarifJournalier <= 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être positif");
        }
        if (jours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être positif");
        }
        if (typeTarif == null || typeTarif.isBlank()) {
            throw new IllegalArgumentException("Le type de tarif est obligatoire");
        }

        StrategieTarification strategie = strategies.get(typeTarif.trim().toUpperCase());
        if (strategie == null) {
            throw new IllegalArgumentException("Type de tarif inconnu : " + typeTarif);
        }
        return strategie.calculer(tarifJournalier, jours);
    }
}