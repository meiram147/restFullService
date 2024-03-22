package org.example.restfullservice.service;

import org.example.restfullservice.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientServiceImpl implements ClientService{
    private static final Map<Integer, Client> CLIENT_MAP = new HashMap<>();

    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void createClient(Client client) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(clientId);
        CLIENT_MAP.put(clientId, client);
    }
    @Override
    public List<Client> clientAll() {
        return new ArrayList<>(CLIENT_MAP.values());
    }

    @Override
    public Client read(int id) {
        return CLIENT_MAP.get(id);
    }

    @Override
    public Boolean update(Client client, int id) {
        if (CLIENT_MAP.containsKey(id)){
            client.setId(id);
            CLIENT_MAP.put(id,client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_MAP.remove(id) !=null;
    }
}
