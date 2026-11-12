package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    @Transactional
    public Paiement ajouterPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    @Transactional
    public Paiement modifierPaiement(Long idPaiement, Paiement paiement) {
        Paiement existant = recupererPaiement(idPaiement);
        existant.setMontant(paiement.getMontant());
        existant.setDatePaiement(paiement.getDatePaiement());
        existant.setModePaiement(paiement.getModePaiement());
        if (paiement.getContrat() != null) {
            existant.setContrat(paiement.getContrat());
        }
        return paiementRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Paiement recupererPaiement(Long idPaiement) {
        return paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new EntityNotFoundException("Paiement introuvable : " + idPaiement));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> recupererTousLesPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerPaiement(Long idPaiement) {
        recupererPaiement(idPaiement);
        paiementRepository.deleteById(idPaiement);
    }
}