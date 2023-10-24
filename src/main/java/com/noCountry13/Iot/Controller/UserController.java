package com.noCountry13.Iot.controller;


import com.noCountry13.Iot.Service.UserService;
import com.noCountry13.Iot.security.entity.Usuario;
import com.noCountry13.Iot.security.jwt.JwtProvider;
import com.noCountry13.Iot.security.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/client")
@AllArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    JwtProvider jwtProvider;

    @GetMapping("")
    public Usuario findByAuthUser (){

        return userService.findByAuthUser(jwtProvider.getAuthUserName());

    }

}
