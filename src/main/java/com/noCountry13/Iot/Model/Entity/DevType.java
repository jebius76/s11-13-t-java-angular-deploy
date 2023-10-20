package com.noCountry13.Iot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "devType")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DevType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    public DevType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
