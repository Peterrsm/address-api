package com.pedromiranda.address_api.client.interfaces;

import com.pedromiranda.address_api.dto.AddressResponse;

public interface ICepClient {

    AddressResponse buscarEndereco(String cep);
}
