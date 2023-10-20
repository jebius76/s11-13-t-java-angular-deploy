package com.noCountry13.Iot.controller;

import com.noCountry13.Iot.dto.PublishMessageDTO;
import com.noCountry13.Iot.mqtt.MqttService;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/mqtt")
@AllArgsConstructor
public class MqttMessageController {

    @Autowired
    MqttService mqttService;

    @PostMapping("")
    public ResponseEntity<?> publish(@Valid @RequestBody PublishMessageDTO publishMessageDto) throws MqttPersistenceException, MqttException {

        if (mqttService.publish(publishMessageDto.getTopic(), publishMessageDto.getPayload(), 0,false))
            return new ResponseEntity("Message published succesfully", HttpStatus.OK);

        return new ResponseEntity("Error publishing message", HttpStatus.CONFLICT);

    }
    
}
