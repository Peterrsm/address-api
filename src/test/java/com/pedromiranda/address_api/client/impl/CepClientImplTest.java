package com.pedromiranda.address_api.client.impl;

import com.pedromiranda.address_api.client.exceptions.CepException;
import com.pedromiranda.address_api.dto.AddressResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CepClientImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CepClientImpl client;

    private final String API_URL = "http://api.teste.com";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(client, "apiUrl", API_URL);
    }

    @Test
    void shouldCallBuscarEnderecoMethodWithSuccess() {
        String cep = "10203040";
        String urlEsperada = API_URL + "/cep/" + cep;
        AddressResponse mockResponse = new AddressResponse("10203040",
                "logradouro",
                "complemento",
                "bairro",
                "localidade",
                "uf");

        when(restTemplate.getForObject(anyString(), eq(AddressResponse.class)))
                .thenReturn(mockResponse);

        AddressResponse resultado = client.buscarEndereco(cep);

        assertThat(resultado).isNotNull();
        verify(restTemplate, times(1)).getForObject(urlEsperada, AddressResponse.class);
        verify(restTemplate).getForObject(urlEsperada, AddressResponse.class);
    }

    @Test
    void shouldThrowCepException() {
        String cep = "102030";
        String urlEsperada = API_URL + "/cep/" + cep;

        assertThrows(CepException.class, () -> {
            client.buscarEndereco(cep);
        });
    }
}