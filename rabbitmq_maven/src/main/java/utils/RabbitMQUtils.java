package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        // 重量级资源，类加载执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("39.97.107.13");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/test");
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("123");
    }

    // 提供连接对象方法
    public static Connection getConnection(){
        try{
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // 关闭通道和连接方法
    public static void closeConnectionAndChannel(Channel channel, Connection conn){
        try {
            if(channel!=null) channel.close();
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
