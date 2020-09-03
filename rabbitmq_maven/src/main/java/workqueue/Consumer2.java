package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;
import java.io.IOException;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.queueDeclare("work",true,false,false,null);

        channel.basicConsume("work",false,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body) throws IOException {
                System.out.println("2号收到新消息：" + new String(body));
                // 手动确认 参数1：确认信息标识 参数2：multiple 是否确认多个
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
