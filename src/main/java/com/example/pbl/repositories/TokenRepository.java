package com.example.pbl.repositories;

import java.util.List;
import java.util.Optional;

import com.example.pbl.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByRevokedFalseAndExpiredFalseAndCitizenId(Long id);

    Optional<Token> findByToken(String token);
}