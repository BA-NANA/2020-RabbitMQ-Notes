package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;
import java.io.IOException;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机及类型
        channel.exchangeDeclare("ming","direct");

        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();

        // 基于路由Key绑定队列和交换机 参数：队列名 交换机名 路由Key
        channel.queueBind(queue,"ming","error");

        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body){
                System.out.println("1收到新消息：" + new String(body));
            }
        });
    }
}
