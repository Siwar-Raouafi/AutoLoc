package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;
import java.util.List;

public interface IContratService {
    Contrat ajouterContrat(Contrat contrat);
    Contrat modifierContrat(Long idContrat, Contrat contrat);
    Contrat recupererContrat(Long idContrat);
    List<Contrat> recupererTousLesContrats();
    void supprimerContrat(Long idContrat);
}