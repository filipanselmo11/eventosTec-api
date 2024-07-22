package com.eventostec.api.domain.endereco;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {}
