package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    @Transactional
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    @Transactional
    public Agence modifierAgence(Long idAgence, Agence agence) {
        Agence existante = recupererAgence(idAgence);
        existante.setNom(agence.getNom());
        existante.setVille(agence.getVille());
        existante.setAdresse(agence.getAdresse());
        existante.setTelephone(agence.getTelephone());
        return agenceRepository.save(existante);
    }

    @Override
    @Transactional(readOnly = true)
    public Agence recupererAgence(Long idAgence) {
        return agenceRepository.findById(idAgence)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable : " + idAgence));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> recupererToutesLesAgences() {
        return agenceRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerAgence(Long idAgence) {
        recupererAgence(idAgence);
        agenceRepository.deleteById(idAgence);
    }
}