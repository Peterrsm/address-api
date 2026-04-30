package com.pedromiranda.address_api.dto;

public record AddressResponse(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
    @Override
    public String toString() {
        return "Endereço Bancário { " +
                "CEP: '" + cep + '\'' +
                ", Cidade: '" + localidade + "/" + uf + '\'' +
                " }";
    }
}