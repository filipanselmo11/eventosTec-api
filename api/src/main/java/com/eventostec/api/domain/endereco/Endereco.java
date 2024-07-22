package com.eventostec.api.domain.endereco;

import java.util.UUID;

import com.eventostec.api.domain.evento.Evento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "endereco")
@Entity
public class Endereco {
    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
}
