package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {

    Vehicule ajouterVehicule(Vehicule vehicule);

    Vehicule modifierVehicule(Long idVehicule, Vehicule vehicule);

    Vehicule recupererVehicule(Long idVehicule);

    List<Vehicule> recupererTousLesVehicules();

    void supprimerVehicule(Long idVehicule);
}