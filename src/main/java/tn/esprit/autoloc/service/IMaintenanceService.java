package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;
import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouterMaintenance(Maintenance maintenance);
    Maintenance modifierMaintenance(Long idMaintenance, Maintenance maintenance);
    Maintenance recupererMaintenance(Long idMaintenance);
    List<Maintenance> recupererToutesLesMaintenances();
    void supprimerMaintenance(Long idMaintenance);
}