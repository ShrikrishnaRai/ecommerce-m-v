package com.shree.ecommerce_m_v.config.security.user.repository;

import com.shree.ecommerce_m_v.config.security.user.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
