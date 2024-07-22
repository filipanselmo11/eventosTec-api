package com.eventostec.api.controller;

import java.util.UUID;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventostec.api.domain.cupom.Cupom;
import com.eventostec.api.domain.cupom.CupomRequestDto;
import com.eventostec.api.services.CupomService;

@RestController
@RequestMapping("/api/cupom")
public class CupomController {
    
    @Autowired
    private CupomService cupomService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Cupom> addCupomEvento(@PathVariable UUID eventId, @RequestBody CupomRequestDto data) {
        Cupom cupons = cupomService.addCupomEvento(eventId, data);
        return ResponseEntity.ok(cupons);
    }
}
