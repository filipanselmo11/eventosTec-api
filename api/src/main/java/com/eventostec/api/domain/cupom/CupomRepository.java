package com.eventostec.api.domain.cupom;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom, UUID> {
    
}
