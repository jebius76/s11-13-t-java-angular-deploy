package com.noCountry13.Iot.security.service;

import com.noCountry13.Iot.security.entity.Usuario;
import com.noCountry13.Iot.security.entity.UsuarioMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getByUsuario(nombreUsuario).get();
		return UsuarioMain.build(usuario);
	}
}
