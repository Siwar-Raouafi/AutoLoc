package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    @Transactional
    public Equipement ajouterEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    @Transactional
    public Equipement modifierEquipement(Long idEquipement, Equipement equipement) {
        Equipement existant = recupererEquipement(idEquipement);
        existant.setLibelle(equipement.getLibelle());
        return equipementRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Equipement recupererEquipement(Long idEquipement) {
        return equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable : " + idEquipement));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> recupererTousLesEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerEquipement(Long idEquipement) {
        recupererEquipement(idEquipement);
        equipementRepository.deleteById(idEquipement);
    }
}