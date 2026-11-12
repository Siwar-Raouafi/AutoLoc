package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;
import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe employe);
    Employe modifierEmploye(Long idEmploye, Employe employe);
    Employe recupererEmploye(Long idEmploye);
    List<Employe> recupererTousLesEmployes();
    void supprimerEmploye(Long idEmploye);
}