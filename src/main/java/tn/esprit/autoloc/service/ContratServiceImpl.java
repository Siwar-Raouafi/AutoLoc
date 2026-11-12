package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    @Override
    @Transactional
    public Contrat ajouterContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    @Transactional
    public Contrat modifierContrat(Long idContrat, Contrat contrat) {
        Contrat existant = recupererContrat(idContrat);
        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.isValide());
        if (contrat.getReservation() != null) {
            existant.setReservation(contrat.getReservation());
        }
        return contratRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Contrat recupererContrat(Long idContrat) {
        return contratRepository.findById(idContrat)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable : " + idContrat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> recupererTousLesContrats() {
        return contratRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerContrat(Long idContrat) {
        recupererContrat(idContrat);
        contratRepository.deleteById(idContrat);
    }
}