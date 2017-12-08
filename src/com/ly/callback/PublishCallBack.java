package com.ly.callback;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/**
 *@jsphlim
 * ps:服务器用的是贻圣师兄的
 */
public class PublishCallBack implements MqttCallback {

    public void connectionLost(Throwable cause) {
        System.out.println("连接断开，请重连");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete" + token.isComplete());
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("消息主题 : " + topic);
        System.out.println("消息Qos : " + message.getQos());
        System.out.println("消息内容 : " + new String(message.getPayload()));
    }
}
