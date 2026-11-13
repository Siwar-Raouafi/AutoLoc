package tn.esprit.autoloc.service.tarification;

import org.springframework.stereotype.Component;

@Component("WEEKEND")
public class TarificationWeekend implements StrategieTarification {
    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.90;
    }
}