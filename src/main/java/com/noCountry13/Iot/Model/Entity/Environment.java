package com.noCountry13.Iot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Clase que representa el ambiente donde esta un dispositivo IoT
 * */
@Entity
@Table(name="environment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre del ambiente
     */
    private String name;
    /**
     * Descripcion del ambiente
     */
    private String description;
    /**
     * Subtema del ambiente
     */
    private String subTopic;
    /**
     * Listado de dispositivos que estan en el ambiente
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "environment_id")
    private List<Device> devices;

//    public Environment(String name, String description, String subTopic) {
//        this.name = name;
//        this.description = description;
//        this.subTopic = subTopic;
//    }
}
