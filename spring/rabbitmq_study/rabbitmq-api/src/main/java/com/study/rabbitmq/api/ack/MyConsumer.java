package com.study.rabbitmq.api.ack;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MyConsumer extends DefaultConsumer {

  private Channel channel;

  public MyConsumer(Channel channel) {
    super(channel);
    this.channel = channel;
  }

  @Override
  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
      throws IOException {
    System.err.println("-----------consume message----------");
    System.err.println("body: " + new String(body));
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if ((Integer) properties.getHeaders().get("num") == 0) {
      // 2:是否批量签收  3：是否重回队列
      channel.basicNack(envelope.getDeliveryTag(), false, true);
    } else {
      channel.basicAck(envelope.getDeliveryTag(), false);
    }
  }

}
