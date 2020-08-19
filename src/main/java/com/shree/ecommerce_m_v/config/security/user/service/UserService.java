package com.shree.ecommerce_m_v.config.security.user.service;

import com.shree.ecommerce_m_v.config.security.user.model.UserDTO;
import com.shree.ecommerce_m_v.config.security.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<UserEntity> activateRegistration(String key);

    Optional<UserEntity> completePasswordReset(String newPassword, String key);

    Optional<UserEntity> requestPasswordReset(String mail);

    UserEntity registerUser(UserDTO userDTO, String password);

    UserEntity createUser(UserDTO userDTO);

    Optional<UserDTO> updateUser(UserDTO userDTO);

    void deleteUser(String login);

    void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl);

    void changePassword(String currentClearTextPassword, String newPassword);

    Page<UserDTO> getAllManagedUsers(Pageable pageable);

    Optional<UserEntity> getUserWithAuthoritiesByLogin(String login);

    Optional<UserEntity> getUserWithAuthorities();

    List<String> getAuthorities();
}
