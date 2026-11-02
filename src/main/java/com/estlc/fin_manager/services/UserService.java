package com.estlc.fin_manager.services;

import com.estlc.fin_manager.dto.UserDto;
import com.estlc.fin_manager.entities.Role;
import com.estlc.fin_manager.entities.User;
import com.estlc.fin_manager.mappers.UserMapper;
import com.estlc.fin_manager.repositories.RoleRepository;
import com.estlc.fin_manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserDto findById(String idUser) {
        return userMapper.toDto(getUser(idUser));
    }

    @Transactional
    public UserDto create(UserDto dto) {
        if (dto.idUser() == null || dto.idUser().isBlank()) {
            throw new IllegalArgumentException("La CNI (idUser) est obligatoire");
        }
        if (userRepository.existsById(dto.idUser())) {
            throw new IllegalArgumentException("Un utilisateur avec cette CNI existe déjà : " + dto.idUser());
        }

        User user = userMapper.toEntity(dto);
        user.setRoles(resolveRoles(dto.roleIds()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDto update(String idUser, UserDto dto) {
        User user = getUser(idUser);
        userMapper.apply(dto, user);
        if (dto.roleIds() != null) {
            user.setRoles(resolveRoles(dto.roleIds()));
        }
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public void delete(String idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new NoSuchElementException("Utilisateur introuvable : " + idUser);
        }
        userRepository.deleteById(idUser);
    }

    private User getUser(String idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur introuvable : " + idUser));
    }

    private List<Role> resolveRoles(Set<Long> roleIds) {
        List<Role> roles = new ArrayList<>();
        if (roleIds == null) {
            return roles;
        }
        for (Long roleId : roleIds) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new NoSuchElementException("Rôle introuvable : " + roleId));
            roles.add(role);
        }
        return roles;
    }
}
