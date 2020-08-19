package com.shree.ecommerce_m_v.config.security.user.repository;

import com.shree.ecommerce_m_v.config.security.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOneByActivationKey(String activationKey);

    List<UserEntity> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndDateCreatedBefore(LocalDate dateTime);

    Optional<UserEntity> findOneByResetKey(String resetKey);

    Optional<UserEntity> findOneByEmailIgnoreCase(String email);

    Optional<UserEntity> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByLogin(String login);


    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<UserEntity> findAllByLoginNot(Pageable pageable, String login);
}
