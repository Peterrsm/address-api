package com.pedromiranda.address_api.controller.interfeces;

import com.pedromiranda.address_api.dto.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/cep")
public interface IAddressApiController {
    @Operation(summary = "Busca endereço por CEP", description = "Consulta o Mockoon e registra log no Postgres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
            @ApiResponse(responseCode = "400", description = "CEP inválido"),
            @ApiResponse(responseCode = "404", description = "CEP não localizado")
    })
    @GetMapping("/{cep}")
    ResponseEntity<AddressResponse> getAddress(@PathVariable("cep") String cep);
}
