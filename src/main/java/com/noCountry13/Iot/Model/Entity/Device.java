package com.noCountry13.Iot.Model.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Clase que representa un dispositivo IoT.
 */
@Entity
@Table(name="device")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Marca del dispositivo.
     */
    private String brand;
    /**
     * Número de serie del dispositivo.
     */
    private String sn;
    /**
     * Tipo de dispositivo (enum DevType).
     */
    @ManyToOne
    @JoinColumn(name = "devType_id")
    private DevType devType;
    /**
     * Descripción del dispositivo.
     */
    private String description;
    /**
     * Subtema del dispositivo.
     */
    private String subtopic;
    /**
     * Cliente MQTT asociado al dispositivo.
     */
    private String mqttClient;
    /**
     * Unidad de medida asociada al dispositivo.
     */
    private String unit;

}
