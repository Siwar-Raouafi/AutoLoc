package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;
import java.util.List;

public interface IClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Long idClient, Client client);
    Client recupererClient(Long idClient);
    List<Client> recupererTousLesClients();
    void supprimerClient(Long idClient);
}