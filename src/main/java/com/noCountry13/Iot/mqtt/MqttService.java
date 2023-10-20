package com.noCountry13.Iot.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MqttService {

    @Autowired
    private IMqttClient mqttClient;

    public void subscribe(final String topic) throws MqttException, InterruptedException {
            mqttClient.subscribe("#",0);
    }

    public boolean publish(final String topic, final String payload, int qos, boolean retained)
            throws MqttPersistenceException, MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        if (mqttClient.isConnected()){
            mqttClient.publish(topic, mqttMessage);
            return true;
        }
        return false;
    }

}
