package tn.esprit.autoloc.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    @Transactional
    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client modifierClient(Long idClient, Client client) {
        Client existant = recupererClient(idClient);
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());
        return clientRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Client recupererClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable : " + idClient));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> recupererTousLesClients() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerClient(Long idClient) {
        recupererClient(idClient);
        clientRepository.deleteById(idClient);
    }
}