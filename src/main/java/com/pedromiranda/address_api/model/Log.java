package com.pedromiranda.address_api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;
    private String cep;
    private String logradouro;
    private String localidade;
    private LocalDateTime consultDate;

    public Log() {
    }

    public Log(String cep, String logradouro, String localidade, LocalDateTime consultDate) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.localidade = localidade;
        this.consultDate = consultDate;
    }
}
