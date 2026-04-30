package com.pedromiranda.address_api.client.impl;

import com.pedromiranda.address_api.client.exceptions.CepException;
import com.pedromiranda.address_api.client.interfaces.ICepClient;
import com.pedromiranda.address_api.dto.AddressResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CepClientImpl implements ICepClient {

    private static final Logger log = LoggerFactory.getLogger(CepClientImpl.class);
    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    public CepClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AddressResponse buscarEndereco(String cep) {

        String url = apiUrl + "/cep/" + cep;

        try {
            validarCep(cep);

            log.info("Iniciando chamada para {}", url);

            return restTemplate.getForObject(url, AddressResponse.class);
        } catch (Exception e) {
            System.err.println("Erro na chamada: " + e.getMessage());
            throw e;
        }
    }

    private void validarCep(String cep) {
        log.info("Validando CEP: {}", cep);

        if (!cep.matches("\\d{8}")) {
            throw new CepException();
        }
    }
}
