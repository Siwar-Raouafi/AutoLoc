package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;

    @Override
    @Transactional
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        vehiculeValidator.valider(vehicule);
        journalisationService.journaliser("Ajout du véhicule : " + vehicule.getImmatriculation());
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule modifierVehicule(Long idVehicule, Vehicule vehicule) {
        vehiculeValidator.valider(vehicule);
        Vehicule existant = recupererVehicule(idVehicule);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());
        if (vehicule.getAgence() != null) {
            existant.setAgence(vehicule.getAgence());
        }
        journalisationService.journaliser("Modification du véhicule id=" + idVehicule);
        return vehiculeRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicule recupererVehicule(Long idVehicule) {
        return vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable : " + idVehicule));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> recupererTousLesVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerVehicule(Long idVehicule) {
        recupererVehicule(idVehicule);
        journalisationService.journaliser("Suppression du véhicule id=" + idVehicule);
        vehiculeRepository.deleteById(idVehicule);
    }
}