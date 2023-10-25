package com.noCountry13.Iot.security.jwt;


import com.noCountry13.Iot.security.dto.RefreshTokenDto;
import com.noCountry13.Iot.security.entity.UsuarioMain;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Clase que genera el token y valida que este bien formado y no este expirado
 */
@Component
public class JwtProvider {

    // Implementamos un logger para ver cual metodo da error en caso de falla
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //Valores que tenemos en el aplicattion.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    /**
     *setIssuedAt --> Asigna fecha de creción del token
     *setExpiration --> Asigna fehca de expiración
     * signWith --> Firma
     */
    public String generateToken(Authentication authentication){
        UsuarioMain usuarioMain = (UsuarioMain) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioMain.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .claim("rol", usuarioMain.getAuthorities())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public RefreshTokenDto refreshToken(String token) {
        var tokenJwt = token.replace("Bearer ", "");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(tokenJwt)
                    .getBody();

            // Obtén el nombre de usuario del token actual
            String username = claims.getSubject();
            var roles = claims.get("rol", List.class);  // Obtiene las reclamaciones de autoridad
            var rol = roles.get(0);
            // Genera un nuevo token con la misma información, incluyendo las reclamaciones de autoridad
            String refreshedToken = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                    .claim("rol", roles)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

            RefreshTokenDto refreshTokenDto = new RefreshTokenDto(refreshedToken, username, rol);
            return refreshTokenDto;
        } catch (JwtException e) {
            // Maneja excepciones si el token no es válido
            logger.error("Error al refrescar el token: " + e.getMessage());
            return null; // Devuelve null en caso de error
        }
    }

    //subject --> Nombre del usuario
    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Token mal formado");
        }catch (UnsupportedJwtException e){
            logger.error("Token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("Token expirado");
        }catch (IllegalArgumentException e){
            logger.error("Token vacio");
        }catch (SignatureException e){
            logger.error("Fallo con la firma");
        }
        return false;
    }

    public String getAuthUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

}