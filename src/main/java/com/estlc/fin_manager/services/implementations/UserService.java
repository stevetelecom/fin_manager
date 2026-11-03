package com.estlc.fin_manager.services.implementations;

import com.estlc.fin_manager.dto.AccountResponseDto;
import com.estlc.fin_manager.dto.UserRequestDto;
import com.estlc.fin_manager.dto.UserResponseDto;
import com.estlc.fin_manager.entities.User;
import com.estlc.fin_manager.mappers.UserMapper;
import com.estlc.fin_manager.repositories.UserRepository;
import com.estlc.fin_manager.services.interfaces.UserServiceInterface;
import com.estlc.fin_manager.utils.Generateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private static final int CNI_LENGTH = 5;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Generateur generateur;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(String idUser) {
        return userMapper.toDTO(getUser(idUser));
    }

    @Override
    @Transactional
    public UserResponseDto saveUser(UserRequestDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Le DTO utilisateur est obligatoire");
        }
        if (dto.password() == null || dto.password().isBlank()) {
            throw new IllegalArgumentException("Le mot de passe (password) est obligatoire");
        }

        User user = userMapper.toDTOEntity(dto);
        if (user.getIdUser() == null || user.getIdUser().isBlank()) {
            user.setIdUser(generateur.generateCni("USER", CNI_LENGTH));
        } else if (userRepository.existsById(user.getIdUser())) {
            throw new IllegalArgumentException("Un utilisateur avec cette CNI existe déjà : " + user.getIdUser());
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponseDto saveUser(UserResponseDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Le DTO utilisateur est obligatoire");
        }
        UserRequestDto request = new UserRequestDto(
                dto.idUser(),
                dto.nomUser(),
                dto.prenomUser(),
                dto.email(),
                dto.phone(),
                dto.dateN(),
                dto.sexe(),
                dto.profession(),
                dto.password(),
                dto.roleIds()
        );
        return saveUser(request);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(String idUser, UserRequestDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Le DTO utilisateur est obligatoire");
        }
        User user = getUser(idUser);
        userMapper.updateEntityFromDto(dto, user);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(String idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new NoSuchElementException("Utilisateur introuvable : " + idUser);
        }
        userRepository.deleteById(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponseDto> getAllAccounts(String idUser) {
        return userMapper.toAccountDTOList(getUser(idUser).getAccounts());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsersByToken(String token) {
        // TODO authentification : le token n'existe pas encore dans l'entite User.
        // Dans cette version, le token correspond a la CNI de l'utilisateur.
        return userRepository.findById(token)
                .map(user -> List.of(userMapper.toDTO(user)))
                .orElse(List.of());
    }

    private User getUser(String idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur introuvable : " + idUser));
    }
}
