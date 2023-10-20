package com.noCountry13.Iot.mqtt;

import com.noCountry13.Iot.Model.Entity.Iot;
import com.noCountry13.Iot.Service.Implements.IoTServiceImpl;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class MqttConfig {

    @Autowired
    private IoTServiceImpl ioTService;

    @Bean
    @ConfigurationProperties(prefix = "mqtt")
    public MqttConnectOptions mqttConnectOptions() {
        return new MqttConnectOptions();
    }

    @Bean
    public IMqttClient mqttClient(@Value("${mqtt.clientId}") String clientId,
                                  @Value("${mqtt.hostname}") String hostname, @Value("${mqtt.port}") int port) throws MqttException {

        IMqttClient mqttClient = new MqttClient("tcp://" + hostname + ":" + port, clientId);
        mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                try {
                    mqttClient.subscribe("NC/#",0);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Mqtt conection lost:" + throwable);
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

                System.out.println("MQTT Topic: " + s + "Payload: " + new String(mqttMessage.getPayload()));

                if (!s.startsWith("NC/DATA/")) return;

                Iot iot = new Iot();
                iot.setDate(LocalDateTime.now());
                iot.setTopic(s);
                iot.setPayload(new String(mqttMessage.getPayload()));
                ioTService.save(iot);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        mqttClient.connect(mqttConnectOptions());
        return mqttClient;
    }
}
