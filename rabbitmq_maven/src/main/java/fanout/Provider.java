package fanout;

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
        Channel channel = connection.createChannel();

        // 将通道声明指定交换机 参数1：交换机名称 参数2：交换机类型 fanout为广播
        channel.exchangeDeclare("login","fanout");

        // 发送消息
        channel.basicPublish("login","",null,"fanout type message".getBytes());

        //释放资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);

    }
}