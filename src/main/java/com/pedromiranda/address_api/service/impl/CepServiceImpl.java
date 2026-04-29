package com.pedromiranda.address_api.service.impl;

import com.pedromiranda.address_api.client.interfaces.ICepClient;
import com.pedromiranda.address_api.dto.AddressResponse;
import com.pedromiranda.address_api.model.Log;
import com.pedromiranda.address_api.repository.LogRepository;
import com.pedromiranda.address_api.service.interfaces.ICepService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CepServiceImpl implements ICepService {

    private final LogRepository logRepository;
    private final ICepClient cepClient;

    public CepServiceImpl(ICepClient cepClient, LogRepository logRepository) {
        this.cepClient = cepClient;
        this.logRepository = logRepository;
    }

    @Override
    public AddressResponse buscarEndereco(String cep) {

        AddressResponse response = cepClient.buscarEndereco(cep);

        Log log = new Log(
                response.cep(),
                response.logradouro(),
                response.localidade(),
                LocalDateTime.now());

        logRepository.save(log);

        return response;
    }
}
