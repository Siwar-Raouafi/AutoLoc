package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;
import java.util.List;

public interface IReservationService {
    Reservation ajouterReservation(Reservation reservation);
    Reservation modifierReservation(Long idReservation, Reservation reservation);
    Reservation recupererReservation(Long idReservation);
    List<Reservation> recupererToutesLesReservations();
    void supprimerReservation(Long idReservation);
}