package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;
import java.util.List;

public interface IAgenceService {
    Agence ajouterAgence(Agence agence);
    Agence modifierAgence(Long idAgence, Agence agence);
    Agence recupererAgence(Long idAgence);
    List<Agence> recupererToutesLesAgences();
    void supprimerAgence(Long idAgence);
}