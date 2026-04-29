package com.pedromiranda.address_api.client.impl;

import com.pedromiranda.address_api.client.interfaces.ICepClient;
import com.pedromiranda.address_api.dto.AddressResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CepClientImpl implements ICepClient {

    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    public CepClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AddressResponse buscarEndereco(String cep) {
        String url = apiUrl + "/cep/" + cep;
        return restTemplate.getForObject(url, AddressResponse.class);
    }
}
