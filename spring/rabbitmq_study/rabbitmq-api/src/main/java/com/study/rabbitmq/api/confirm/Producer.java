package com.study.rabbitmq.api.confirm;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

  public static void main(String[] args) throws Exception {

    //1 创建ConnectionFactory
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("111.230.115.242");
    connectionFactory.setUsername("feibai");
    connectionFactory.setPassword("123456");
    connectionFactory.setPort(5672);
    connectionFactory.setVirtualHost("/");

    //2 获取C	onnection
    Connection connection = connectionFactory.newConnection();

    //3 通过Connection创建一个新的Channel
    Channel channel = connection.createChannel();

    //4 指定我们的消息投递模式: 消息的确认模式
    channel.confirmSelect();

    String exchangeName = "test_confirm_exchange";
    String routingKey = "confirm.save";

    //5 发送一条消息
    String msg = "Hello RabbitMQ Send confirm message!";
    channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

    //6 添加一个确认监听
    channel.addConfirmListener(new ConfirmListener() {
      @Override
      public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        System.err.println("-------no ack!-----------");//失败的时候
      }

      @Override
      public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        System.err.println("-------ack!-----------");//成功的时候
      }
    });

  }
}
