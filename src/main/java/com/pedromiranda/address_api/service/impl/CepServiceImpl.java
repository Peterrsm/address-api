package com.pedromiranda.address_api.service.impl;

import com.pedromiranda.address_api.client.interfaces.ICepClient;
import com.pedromiranda.address_api.dto.AddressResponse;
import com.pedromiranda.address_api.model.Log;
import com.pedromiranda.address_api.repository.LogRepository;
import com.pedromiranda.address_api.service.interfaces.ICepService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CepServiceImpl implements ICepService {

    private static final Logger log = LoggerFactory.getLogger(CepServiceImpl.class);
    private final LogRepository logRepository;
    private final ICepClient cepClient;

    public CepServiceImpl(ICepClient cepClient, LogRepository logRepository) {
        this.cepClient = cepClient;
        this.logRepository = logRepository;
    }

    @Override
    public AddressResponse buscarEndereco(String cep) {

        AddressResponse response = cepClient.buscarEndereco(cep);

        log.info("CEP {} localizado com sucesso: {}", cep, response.toString());

        Log log = new Log(
                response.cep(),
                response.logradouro(),
                response.localidade(),
                LocalDateTime.now());

        logRepository.save(log);

        return response;
    }
}
