package helloworld;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
//        // 创建连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("39.97.107.13");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/test");
//        connectionFactory.setUsername("test");
//        connectionFactory.setPassword("123");
//
//        // 创建连接对象
//        Connection connection = connectionFactory.newConnection();

        Connection connection = RabbitMQUtils.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        // 通道绑定对象
        channel.queueDeclare("hello",true,false,false,null);

        // 消费消息
        // 参数1：队列名
        // 参数2：是否开启消息自动确认
        // 参数3：消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            // 最后一个参数为消息队列取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body){
                System.out.println("收到新消息：" + new String(body));
            }
        });

        // 不建议close通道和连接，因为需要监听消息
        //channel.close();
        //connection.close();
    }
}
