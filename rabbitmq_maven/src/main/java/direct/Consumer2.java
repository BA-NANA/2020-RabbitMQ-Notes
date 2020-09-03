package direct;

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
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("ming","direct");

        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,"ming","info");
        channel.queueBind(queue,"ming","warning");

        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body){
                System.out.println("2收到新消息：" + new String(body));
            }
        });
    }
}
