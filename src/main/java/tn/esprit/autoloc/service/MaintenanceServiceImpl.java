package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    @Transactional
    public Maintenance ajouterMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    @Override
    @Transactional
    public Maintenance modifierMaintenance(Long idMaintenance, Maintenance maintenance) {
        Maintenance existante = recupererMaintenance(idMaintenance);
        existante.setDateDebut(maintenance.getDateDebut());
        existante.setDateFin(maintenance.getDateFin());
        existante.setDescription(maintenance.getDescription());
        if (maintenance.getVehicule() != null) {
            existante.setVehicule(maintenance.getVehicule());
        }
        return maintenanceRepository.save(existante);
    }

    @Override
    @Transactional(readOnly = true)
    public Maintenance recupererMaintenance(Long idMaintenance) {
        return maintenanceRepository.findById(idMaintenance)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable : " + idMaintenance));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> recupererToutesLesMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerMaintenance(Long idMaintenance) {
        recupererMaintenance(idMaintenance);
        maintenanceRepository.deleteById(idMaintenance);
    }
}