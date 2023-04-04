package com.assignment.insurance.controller;

import com.assignment.insurance.exception.ResourceNotFoundException;
import com.assignment.insurance.model.Client;
import com.assignment.insurance.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class ClientController {
   private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(path="/clients")
    public List<Client> getClients(){
        return clientService.getClients();
    }
    @GetMapping(path="/client/{id}")
    public Optional<Client> getClient(@PathVariable(value="id") Long id) {
        return clientService.getClientById(id);
    }
    @PostMapping(path = "/client")
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);

    }

    @PutMapping("Uclient/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client clientDetails) {
        Client existingClient = clientService.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not exist with id: " + id));

        if (clientDetails.getName() != null) {
            existingClient.setName(clientDetails.getName());
        }
        if (clientDetails.getAddress() != null) {
            existingClient.setAddress(clientDetails.getAddress());
        }
        if (clientDetails.getDob() != null) {
            existingClient.setDob(clientDetails.getDob());
        }
        if (clientDetails.getContact() != null) {
            existingClient.setContact(clientDetails.getContact());
        }

        clientService.save(existingClient);

        return ResponseEntity.ok(existingClient);
    }

    @DeleteMapping(path = "/Dclient/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable long id){

        Client client = clientService.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("client not exist with id: " + id));

        clientService.delete(client);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
