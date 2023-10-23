package com.noCountry13.Iot.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Builder
@Getter @Setter
public class MessageResponse {
	 private String message;
}
