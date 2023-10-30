package com.noCountry13.Iot.Service.Implements;

import com.noCountry13.Iot.Service.UserService;
import com.noCountry13.Iot.security.entity.Rol;
import com.noCountry13.Iot.security.entity.Usuario;
import com.noCountry13.Iot.security.enums.RolNombre;
import com.noCountry13.Iot.security.repository.RolRepository;
import com.noCountry13.Iot.security.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Override
    public Usuario findByAuthUser(String usuario) {
        return usuarioRepository.findByNombreUsuario(usuario).get();
    }
    public List<Usuario> findAllClients(){
        return usuarioRepository.findAllByRolesContaining(rolRepository.findByRolNombre(RolNombre.ROLE_USER).get());
    }
}
