package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    @Transactional
    public Employe ajouterEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe modifierEmploye(Long idEmploye, Employe employe) {
        Employe existant = recupererEmploye(idEmploye);
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());
        if (employe.getAgence() != null) {
            existant.setAgence(employe.getAgence());
        }
        return employeRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Employe recupererEmploye(Long idEmploye) {
        return employeRepository.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable : " + idEmploye));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> recupererTousLesEmployes() {
        return employeRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerEmploye(Long idEmploye) {
        recupererEmploye(idEmploye);
        employeRepository.deleteById(idEmploye);
    }
}