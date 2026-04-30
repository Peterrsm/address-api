package com.pedromiranda.address_api.service.impl;

import com.pedromiranda.address_api.client.interfaces.ICepClient;
import com.pedromiranda.address_api.dto.AddressResponse;
import com.pedromiranda.address_api.model.Log;
import com.pedromiranda.address_api.repository.LogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CepServiceImplTest {

    @Mock
    private ICepClient cepClient;

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private CepServiceImpl service;

    @Test
    void shouldCallBuscarEnderecoMethodWithSuccess() {
        String cep = "10203040";
        AddressResponse mockResponse = new AddressResponse("10203040",
                "logradouro",
                "complemento",
                "bairro",
                "localidade",
                "uf");

        when(cepClient.buscarEndereco(cep)).thenReturn(mockResponse);

        AddressResponse resultado = service.buscarEndereco(cep);

        assertThat(resultado).isNotNull();
        assertThat(resultado.cep()).isEqualTo(cep);

        verify(cepClient, times(1)).buscarEndereco(cep);

        verify(logRepository, times(1)).save(any(Log.class));
    }
}