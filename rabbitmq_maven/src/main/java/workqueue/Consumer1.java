package workqueue;

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
        final Channel channel = connection.createChannel();

        channel.basicQos(1); //每次只消费1个消息

        channel.queueDeclare("work",true,false,false,null);

        channel.basicConsume("work",false,new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[]body) throws IOException {
                System.out.println("1号收到新消息：" + new String(body));
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
