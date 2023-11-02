package com.noCountry13.Iot.Model.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.noCountry13.Iot.Model.Entity.Environment;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HouseDto {
    @NotBlank(message = "client not by empty")
    private String address;
    @NotBlank(message = "descriptions not by empty")
    private String description;
    @NotBlank(message = "subtopic not by empty")
    private String subtopic;
    private List<Environment> environments;
}

