package com.eventostec.api.domain.cupom;

public record CupomRequestDto(String code, Integer discount, Long valid) {
    
}
