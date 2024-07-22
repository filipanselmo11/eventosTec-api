package com.eventostec.api.services;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventostec.api.domain.cupom.Cupom;
import com.eventostec.api.domain.cupom.CupomRepository;
import com.eventostec.api.domain.cupom.CupomRequestDto;
import com.eventostec.api.domain.evento.Evento;
import com.eventostec.api.domain.evento.EventoRepository;

@Service
public class CupomService {
    
    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Cupom addCupomEvento(UUID eventId, CupomRequestDto data) {
        Evento evento = eventoRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found"));
        Cupom cupom = new Cupom();
        cupom.setCode(data.code());
        cupom.setDiscount(data.discount());
        cupom.setValid(new Date(data.valid()));
        cupom.setEvento(evento);

        return cupomRepository.save(cupom);
    }
}
