package com.example.pbl.service;

import com.example.pbl.entity.RefreshToken;
import com.example.pbl.exception.TokenRefreshException;
import com.example.pbl.repositories.CitizenRepository;
import com.example.pbl.repositories.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private Long refreshTokenDurationMs=Long.valueOf(86400000);

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setCitizen(citizenRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByCitizen(citizenRepository.findById(userId).get());
    }
}
