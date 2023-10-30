package com.noCountry13.Iot.security.repository;

import com.noCountry13.Iot.security.entity.Rol;
import com.noCountry13.Iot.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	boolean existsByNombreUsuario (String nombreUsuario);
	boolean existsByEmail (String email);

    List<Usuario> findAllByRolesContaining(Rol rol);
}