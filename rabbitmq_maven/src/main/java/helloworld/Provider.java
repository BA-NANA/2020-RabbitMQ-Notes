package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

//        //1. 创建连接mq的连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//
//        //2. 设置连接rabbitmq主机
//        connectionFactory.setHost("39.97.107.13");
//
//        //3. 设置端口号
//        connectionFactory.setPort(5672);
//
//        //4. 设置连接哪个虚拟主机
//        connectionFactory.setVirtualHost("/test");
//
//        //5. 设置用户名和密码
//        connectionFactory.setUsername("test");
//        connectionFactory.setPassword("123");

//        //6. 获取连接对象
//        Connection connection = connectionFactory.newConnection();

        Connection connection = RabbitMQUtils.getConnection();

        //7. 获取连接中通道
        Channel channel = connection.createChannel();

        //8. 通道绑定对应消息队列
        //参数1：队列名，不存在时会自动创建
        //参数2：定义队列是否要持久化
        //参数3：exclusive 是否独占队列
        //参数4：autoDelete 消费完成后是否自动删除队列
        //参数5：附加参数
        channel.queueDeclare("hello",true,false,false,null);

        //9. 发布消息
        //参数1：交换机名称，没有则不填
        //参数2：队列名称
        //参数3：额外参数，可以设置消息持久化
        //参数4：消息内容，注意转换为字节
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"你好，rabbitmq！".getBytes());

//        //10. 关闭资源
//        channel.close();
//        connection.close();

        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
