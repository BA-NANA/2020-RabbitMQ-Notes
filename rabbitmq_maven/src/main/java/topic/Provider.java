package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;
import java.io.IOException;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机及类型
        channel.exchangeDeclare("topic_exchange","topic");

        // 发布消息
        String routingKey = "banana.book.hello";

        channel.basicPublish("topic_exchange",routingKey,null,("topic动态路由模型，routingKey:"+routingKey).getBytes());

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
