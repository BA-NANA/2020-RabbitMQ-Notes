package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMQUtils;
import java.io.IOException;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class Provider {

    public static void main(String[] args) throws IOException {

        // 获取连接与通道
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);

        // 生产消息
        for(int i=0; i<20; i++){
            channel.basicPublish("","work", null,(i + "你好，work queue!").getBytes());
        }

        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
