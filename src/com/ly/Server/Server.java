package com.ly.Server;

import com.ly.callback.PublishCallBack;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;

/**
  *@jsphlim
 * ps:服务器用的是贻圣师兄的
 */
public class Server {

    public static final String HOST = "tcp://47.52.33.76:1883";
    public static final String TOPIC = "CMD";
    private static final String clientid = "HControl_" + new Date().getTime();

    private MqttClient client;
    private MqttTopic topic;
    private String userName = "eysam";
    private String passWord = "123456";

    private MqttMessage message;

    public Server() throws MqttException {
        client = new MqttClient(HOST,clientid, new MemoryPersistence());
        connect();
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PublishCallBack());
            client.connect(options);
            topic = client.getTopic(TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("消息发布成功! "
                + token.isComplete());
    }

    public static void main(String[] args) throws MqttException {
        Server server = new Server();

        server.message = new MqttMessage();
        server.message.setQos(2);
        server.message.setRetained(true);
        server.message.setPayload("哈哈哈哈".getBytes());
        server.publish(server.topic , server.message);

        server.message = new MqttMessage();
        server.message.setQos(2);
        server.message.setRetained(true);
        server.message.setPayload("推送一条信息".getBytes());
        server.publish(server.topic , server.message);

    }
}