package com.noCountry13.Iot.security.service;


import com.noCountry13.Iot.security.entity.Usuario;
import com.noCountry13.Iot.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Optional<Usuario> getByUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	public Boolean existsByUsuario(String nombreUsuario){
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}

	public Boolean existsByEmail(String email){
		return usuarioRepository.existsByEmail(email);
	}

	public Usuario save(Usuario usuario){
		return usuarioRepository.save(usuario);
	}


}
