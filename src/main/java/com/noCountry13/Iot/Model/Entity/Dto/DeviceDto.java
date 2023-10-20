package com.noCountry13.Iot.Model.Entity.Dto;

import com.noCountry13.Iot.Model.Entity.DevType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    @NotBlank
    private  String brand;
    @NotBlank
    private  String sn;
    @NotBlank
    private DevType type;
    @NotBlank
    private String description;
    @NotBlank
    private String subtopic;
    @NotBlank
    private String mqttClient;
    @NotBlank
    private String unit;
}
