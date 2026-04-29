package com.pedromiranda.address_api.controller.interfeces;

import com.pedromiranda.address_api.dto.AddressResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/cep")
public interface ITraceRouteController {

    @GetMapping("/{cep}")
    ResponseEntity<AddressResponse> getAddress(@PathVariable("cep") String cep);
}
