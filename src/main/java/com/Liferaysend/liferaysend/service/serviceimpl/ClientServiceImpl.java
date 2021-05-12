package com.Liferaysend.liferaysend.service.serviceimpl;

import com.Liferaysend.liferaysend.model.client;
import com.Liferaysend.liferaysend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl {
    @Autowired
    private ClientRepository clientRepository;
    public void StoreClient(client client)
    {
        clientRepository.save(client);
    }
}
