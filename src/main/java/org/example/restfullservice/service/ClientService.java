package org.example.restfullservice.service;

import org.example.restfullservice.model.Client;

import java.util.List;

public interface ClientService {
    void createClient(Client client);
    List<Client> clientAll();
    Client read(int id);
    Boolean update(Client client, int id);
    boolean delete(int id);
}
