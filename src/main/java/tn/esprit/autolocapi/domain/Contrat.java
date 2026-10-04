package tn.esprit.autolocapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false)
    private LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private boolean valide;

    // 1 Contrat -> 1 Reservation (côté propriétaire)
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation", nullable = false, unique = true)
    private Reservation reservation;

    // 1 Contrat -> N Paiement
    @ToString.Exclude
    @OneToMany(mappedBy = "contrat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Paiement> paiements = new ArrayList<>();
}