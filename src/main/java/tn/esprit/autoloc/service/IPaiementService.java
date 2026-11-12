package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;
import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement(Paiement paiement);
    Paiement modifierPaiement(Long idPaiement, Paiement paiement);
    Paiement recupererPaiement(Long idPaiement);
    List<Paiement> recupererTousLesPaiements();
    void supprimerPaiement(Long idPaiement);
}