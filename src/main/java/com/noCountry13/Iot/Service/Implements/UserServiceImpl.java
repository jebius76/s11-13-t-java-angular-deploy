package com.noCountry13.Iot.Service.Implements;

import com.noCountry13.Iot.Service.UserService;
import com.noCountry13.Iot.security.entity.Usuario;
import com.noCountry13.Iot.security.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UsuarioRepository usuarioRepository;
    @Override
    public Usuario findByAuthUser(String usuario) {
        return usuarioRepository.findByNombreUsuario(usuario).get();
    }
}
