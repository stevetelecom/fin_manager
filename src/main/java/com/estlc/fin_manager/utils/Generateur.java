package com.estlc.fin_manager.utils;

import com.estlc.fin_manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Genere des CNI uniques pour les utilisateurs.
 * Decore comme service Spring pour pouvoir etre injecte dans UserService.
 */
@Service
@RequiredArgsConstructor
public class Generateur {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DEFAULT_PREFIX = "USR";
    private static final int DEFAULT_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final UserRepository userRepository;

    public String generateCni() {
        return generateCni(DEFAULT_PREFIX, DEFAULT_LENGTH);
    }

    public String generateCni(int length) {
        return generateCni(DEFAULT_PREFIX, length);
    }

    /**
     * Genere une CNI du type ROLE_XXXXXXXXXX en verifiant qu'elle n'existe pas deja.
     */
    public String generateCni(String roleLabel, int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("La longueur de la CNI doit etre positive");
        }

        String prefix = normalizePrefix(roleLabel);
        String cni;
        do {
            cni = prefix + "_" + randomString(length);
        } while (userRepository.existsById(cni));
        return cni;
    }

    private String normalizePrefix(String roleLabel) {
        if (roleLabel == null || roleLabel.isBlank()) {
            return DEFAULT_PREFIX;
        }
        String prefix = roleLabel.trim().toUpperCase().replaceAll("[^A-Z0-9]+", "_");
        return prefix.isBlank() ? DEFAULT_PREFIX : prefix;
    }

    private String randomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return builder.toString();
    }
}
