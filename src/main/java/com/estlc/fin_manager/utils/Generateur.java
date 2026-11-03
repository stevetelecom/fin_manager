package com.estlc.fin_manager.utils;

import com.estlc.fin_manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Generateur {

    private final UserRepository usersRepository;

    public String generateCni(String roleLabel, int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
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
