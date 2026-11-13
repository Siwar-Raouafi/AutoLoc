package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

@Component("LONGUE_DUREE")
public class TarificationLongueDuree implements StrategieTarification {
    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.80;
    }
}