package com.pedromiranda.address_api.client.exceptions;

import lombok.Data;

@Data
public class CepException extends RuntimeException {

    public CepException() {
        super("CEP inválido: deve conter exatamente 8 dígitos numéricos");
    }
}
