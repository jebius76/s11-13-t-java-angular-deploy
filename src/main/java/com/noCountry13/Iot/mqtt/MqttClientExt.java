package com.noCountry13.Iot.mqtt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public class MqttClientExt extends MqttClient {

    private String response;

    public MqttClientExt(String serverURI, String clientId, String actionTopic, String responseTopic, String topic, String value) throws MqttException {

        super(serverURI, clientId);

        this.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                try {
                    subscribe("NC/" + responseTopic + "/" + topic);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
                try {
                    publish("NC/" + actionTopic + "/" + topic, new MqttMessage(value.getBytes()));
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
                setResponse(new String(mqttMessage.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
