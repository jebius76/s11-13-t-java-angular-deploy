package com.noCountry13.Iot.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "iot")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIoT;

    @Column(name = "date")
    private Date date;

    @Column(name = "topic")
    private String topic;

    @Column(name = "Payload")
    private String Payload;
}
