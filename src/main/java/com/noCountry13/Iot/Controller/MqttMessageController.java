package com.noCountry13.Iot.controller;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.MessageDto;
import com.noCountry13.Iot.Service.Implements.DeviceServiceImplement;
import com.noCountry13.Iot.mqtt.MqttClientExt;
import com.noCountry13.Iot.Model.Entity.Dto.PublishMessageDTO;
import com.noCountry13.Iot.mqtt.MqttService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Api(tags = "Mqtt Device Controller", description = "Permite la interaccion del front end con los dispositivos IoT")
public class MqttMessageController {

    @Autowired
    MqttService mqttService;

    @Autowired
    MqttConnectOptions mqttConnectOptions;

    @Autowired
    DeviceServiceImplement deviceService;

    @Autowired
    private Environment env;

    @Operation(summary = "Se utiliza para enviar mensajes mqtt a los dispositivos",
            description = "<p>Solo accesible para administradores.</p>"
    )

    @PostMapping("/mqtt")
    public ResponseEntity<?> publish(@Valid @RequestBody PublishMessageDTO publishMessageDto) throws MqttPersistenceException, MqttException {

        if (mqttService.publish(publishMessageDto.getTopic(), publishMessageDto.getPayload(), 0,false))
            return new ResponseEntity("Message published succesfully", HttpStatus.OK);

        return new ResponseEntity("Error publishing message", HttpStatus.CONFLICT);

    }

    @Operation(summary = "Se utiliza para enviar mensajes mqtt a los dispositivos y obtener una respuesta",
               description = "<p>Los mensajes constan de una accion y un valor.</p>"
                       )
    @PostMapping("/client/mqtt")
    public ResponseEntity<?> control(@RequestParam @Parameter(name = "deviceId", description = "Id del dispositivo a consultar") Long deviceId,
                                     @RequestParam @Parameter(name = "action", description = "<p><b>STATE :</b> Solicita al dispositivo que informe el estado en que se encuentra (no necesita payload)</p>" +
                                             "<p>y devuelve el estado en que se encuentra el dispositivo.</p>" +
                                             "<p><b>ACTION :</b> Solicita al dispositivo que realice la accion enviada en el payload (ej. 1 - encender, 0 - apagar</p>" +
                                             "<p>y devuelve el estado en que queda el dispositivo.</p>" +
                                             "<p><b>LIVE :</b> Solicita al dispositivo que envie una medicion (funciona solo con dispositivos Sensor)</p>" +
                                             "<p>y devuelve la medicion en tiempo real.</p>") String action,
                                     @RequestParam @Nullable @Parameter(name = "value", description = "Valor que se envia al dispositivo, varia segun cada uno") String value){

        Device device = deviceService.findById(deviceId).orElseThrow(()-> new IllegalArgumentException("Device not found"));

        if (device.getSubtopic()==null) {
            return new ResponseEntity<>("Device topic must not be null", HttpStatus.BAD_REQUEST);
        }

        String actionTopic;
        String responseTopic;


        switch (action){
            case "STATE":
                actionTopic = "STATE";
                responseTopic = "STATESEND";
                break;
            case "ACTION":
                actionTopic = "ACTION";
                responseTopic = "STATESEND";
                break;
            case "LIVE":
                actionTopic = "LIVE";
                responseTopic = "LIVESEND";
                break;
            default:
                return new ResponseEntity<>(new MessageDto("Action must be one of [STATE, ACTION, LIVE]"), HttpStatus.BAD_REQUEST);
        }
        String host = "tcp://" + env.getProperty("mqtt.hostname") + ":" + env.getProperty("mqtt.port");

        try {
            MqttClientExt mqttClientExt = new MqttClientExt(
                    host,
                    RandomStringUtils.randomAlphabetic(10),
                    actionTopic,
                    responseTopic,
                    device.getSubtopic(),
                    value);
            mqttClientExt.connect(mqttConnectOptions);
            int i = 0;
            while ((mqttClientExt.getResponse() == null) && (i < 20))
            {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    i ++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            mqttClientExt.disconnect();
            if (mqttClientExt.getResponse() != null) {
                return new ResponseEntity<>(new MessageDto(mqttClientExt.getResponse()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new MessageDto("No response"), HttpStatus.REQUEST_TIMEOUT);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
