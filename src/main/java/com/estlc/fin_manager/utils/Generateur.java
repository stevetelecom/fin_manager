package com.estlc.fin_manager.utils;

import com.estlc.fin_manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Generateur {

    private final UserRepository usersRepository;

    public String generateCni(String roleLabel, int length) {
        // Charset alphanumerique : evite #, %, @, etc. dans les URLs (/api/users/{cni}).
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            randomString.append(chars.charAt(index));
        }

        String cni = roleLabel + "_" + randomString;
        if (usersRepository.existsById(cni)) {
            return generateCni(roleLabel, length);
        }
        return cni;
    }
}
