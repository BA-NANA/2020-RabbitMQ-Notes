package fanout;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;
import java.io.IOException;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class Consumer2 {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 绑定交换机
        channel.exchangeDeclare("login","fanout");

        // 临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 绑定交换机和队列 队列名 交换机名
        channel.queueBind(queueName, "login","");

        // 消费消息
        channel.basicConsume(queueName,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body){
                System.out.println("2收到新消息：" + new String(body));
            }
        });

    }
}
