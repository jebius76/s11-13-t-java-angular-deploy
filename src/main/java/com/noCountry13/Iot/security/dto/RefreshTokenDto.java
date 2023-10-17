package com.noCountry13.Iot.security.dto;

import java.util.List;

public record RefreshTokenDto(String token, String username, Object rol) {
}
