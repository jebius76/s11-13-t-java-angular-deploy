package com.noCountry13.Iot.Model.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class PublishMessageDTO {

    @NotEmpty(message = "Topic must not be either null or empty")
    String topic;
    @NotEmpty(message = "Payload must not be either null or empty")
    String payload;
}
