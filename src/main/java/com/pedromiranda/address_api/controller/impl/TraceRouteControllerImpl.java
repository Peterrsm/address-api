package com.pedromiranda.address_api.controller.impl;

import com.pedromiranda.address_api.controller.interfeces.ITraceRouteController;
import com.pedromiranda.address_api.dto.AddressResponse;
import com.pedromiranda.address_api.service.interfaces.ICepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceRouteControllerImpl implements ITraceRouteController {

    private final ICepService service;

    public TraceRouteControllerImpl(ICepService service) {
        this.service = service;
    }

    public ResponseEntity<AddressResponse> getAddress(@PathVariable String cep) {
        return ResponseEntity.ok(service.buscarEndereco(cep));
    }
}
