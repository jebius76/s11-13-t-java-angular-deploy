package com.noCountry13.Iot.Model.Entity.Dto;

import com.noCountry13.Iot.Model.Entity.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnvironmentDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String subTopic;
    private List<DeviceDto> devices;

//    public EnvironmentDto(String name, String description, String subTopic, Device device) {
//       this.name = name;
//        this.description = description;
//        this.subTopic = subTopic;
//        this.devices.add(device);
//    }
//    public EnvironmentDto(String name, String description, String subTopic) {
//        this.name = name;
//        this.description = description;
//        this.subTopic = subTopic;
//    }
}
