package com.noCountry13.Iot.security.repository;

import com.noCountry13.Iot.security.entity.Rol;
import com.noCountry13.Iot.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}