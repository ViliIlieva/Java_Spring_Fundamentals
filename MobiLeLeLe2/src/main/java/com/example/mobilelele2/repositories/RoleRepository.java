package com.example.mobilelele2.repositories;


import com.example.mobilelele2.domain.enitities.UserRole;
import com.example.mobilelele2.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, String> {

    Optional<UserRole> findByRole(Role role);
}
