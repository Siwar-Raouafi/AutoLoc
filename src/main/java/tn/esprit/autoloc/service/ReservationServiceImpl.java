package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation ajouterReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public Reservation modifierReservation(Long idReservation, Reservation reservation) {
        Reservation existante = recupererReservation(idReservation);
        existante.setDateDebut(reservation.getDateDebut());
        existante.setDateFin(reservation.getDateFin());
        existante.setStatut(reservation.getStatut());
        if (reservation.getClient() != null) {
            existante.setClient(reservation.getClient());
        }
        if (reservation.getVehicule() != null) {
            existante.setVehicule(reservation.getVehicule());
        }
        return reservationRepository.save(existante);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation recupererReservation(Long idReservation) {
        return reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Reservation introuvable : " + idReservation));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> recupererToutesLesReservations() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerReservation(Long idReservation) {
        recupererReservation(idReservation);
        reservationRepository.deleteById(idReservation);
    }
}