package com.eventostec.api.domain.evento;



import org.springframework.web.multipart.MultipartFile;

public record EventoRequestDto(String title, String description, Long date, String city, String state, Boolean remote,
        String eventoUrl, MultipartFile image) {

}
