package topic;

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
        channel.exchangeDeclare("topic_exchange","topic");

        // 创建临时队列
        String queueName = channel.queueDeclare().getQueue();

        // 绑定队列和交换机 动态通配符形式
        channel.queueBind(queueName,"topic_exchange","banana.*");

        // 消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body){
                System.out.println("1收到新消息：" + new String(body));
            }
        });
    }
}
