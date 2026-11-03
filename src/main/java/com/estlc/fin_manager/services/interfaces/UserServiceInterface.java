package com.estlc.fin_manager.services.interfaces;

import com.estlc.fin_manager.dto.AccountResponseDto;
import com.estlc.fin_manager.dto.UserRequestDto;
import com.estlc.fin_manager.dto.UserResponseDto;

import java.util.List;

/**
 * Contrat metier de User.
 */
public interface UserServiceInterface {

    UserResponseDto saveUser(UserRequestDto userRequestDto);

    UserResponseDto saveUser(UserResponseDto userResponseDto);

    UserResponseDto updateUser(String idUser, UserRequestDto userRequestDto);

    void deleteUser(String idUser);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(String idUser);

    List<AccountResponseDto> getAllAccounts(String idUser);

    List<UserResponseDto> getUsersByToken(String token);
}
