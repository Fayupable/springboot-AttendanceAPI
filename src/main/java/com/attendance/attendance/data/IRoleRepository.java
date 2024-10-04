package com.attendance.attendance.data;

import com.attendance.attendance.entity.Role;
import com.attendance.attendance.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
