package direct;

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
        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        // 通过连接获取通道
        Channel channel = connection.createChannel();
        // 声明交换机 参数1：交换机名称 参数2：路由模式
        channel.exchangeDeclare("ming", "direct");
        // 发送消息
        String routingKey = "error";
        channel.basicPublish("ming", routingKey, null, ("direct模型发布routingKey为："+routingKey).getBytes());
        // 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
