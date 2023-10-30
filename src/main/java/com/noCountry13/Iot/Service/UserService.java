package com.noCountry13.Iot.Service;

import com.noCountry13.Iot.security.entity.Usuario;

import java.util.List;

public interface UserService {

    public Usuario findByAuthUser(String usuario);

    public List<Usuario> findAllClients();

}
