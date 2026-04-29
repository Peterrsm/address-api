package com.pedromiranda.address_api.service.interfaces;

import com.pedromiranda.address_api.dto.AddressResponse;

public interface ICepService {

    AddressResponse buscarEndereco(String cep);
}
