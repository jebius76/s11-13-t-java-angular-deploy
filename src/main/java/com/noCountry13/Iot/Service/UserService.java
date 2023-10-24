package com.noCountry13.Iot.Service;

import com.noCountry13.Iot.security.entity.Usuario;

public interface UserService {

    public Usuario findByAuthUser(String usuario);

}
