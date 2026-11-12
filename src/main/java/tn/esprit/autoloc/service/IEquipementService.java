package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;
import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    Equipement modifierEquipement(Long idEquipement, Equipement equipement);
    Equipement recupererEquipement(Long idEquipement);
    List<Equipement> recupererTousLesEquipements();
    void supprimerEquipement(Long idEquipement);
}