package com.eventostec.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eventostec.api.domain.evento.Evento;
import com.eventostec.api.domain.evento.EventoRequestDto;
import com.eventostec.api.domain.evento.EventoResponseDto;
import com.eventostec.api.services.EventoService;

@RestController
@RequestMapping("/api/event")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // @PostMapping
    // public ResponseEntity<Evento> criarEvento(@RequestBody EventoRequestDto body)
    // {
    // Evento novoEvento = this.eventoService.criarEvento(body);
    // return ResponseEntity.ok(novoEvento);
    // }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Evento> criarEvento(@RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("date") Long date,
            @RequestParam("city") String city,
            @RequestParam("state") String state,
            @RequestParam("remote") Boolean remote,
            @RequestParam("eventUrl") String eventUrl,
            @RequestParam(value = "image", required = false) MultipartFile file) {
                EventoRequestDto eventoRequestDto = new EventoRequestDto(title, description, date, city, state, remote, eventUrl, file);
                Evento novoEvento = this.eventoService.criarEvento(eventoRequestDto);
                return ResponseEntity.ok(novoEvento);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDto>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<EventoResponseDto> allEvents = this.eventoService.getUpComingEvents(page, size);
        return ResponseEntity.ok(allEvents);
    }

}
