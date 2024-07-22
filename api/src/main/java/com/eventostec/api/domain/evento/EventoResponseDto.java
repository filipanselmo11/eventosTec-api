package com.eventostec.api.domain.evento;

import java.util.UUID;


public record EventoResponseDto(UUID id, String title, String description, String date, String city, String state, Boolean remote,
        String eventoUrl, String imageUrl) {
    
}
