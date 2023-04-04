package com.assignment.insurance.service;

import com.assignment.insurance.model.Client;
import com.assignment.insurance.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository){ this.clientRepository=clientRepository;
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }
    public Optional<Client> getClientById(Long id){
        return clientRepository.findClientById(id);
    }

    public Client createClient(Client client){
        //Logic
        return clientRepository.save(client);


    }
    public Client save(Client client){
        return clientRepository.save(client);
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
