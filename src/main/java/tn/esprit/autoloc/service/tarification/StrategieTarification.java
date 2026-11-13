package tn.esprit.autoloc.service.tarification;

public interface StrategieTarification {
    double calculer(double tarifJournalier, int jours);
}