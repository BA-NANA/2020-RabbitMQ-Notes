package com.banana.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */
@Component
public class RouteConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "direct_exchange", type = "direct"), // 指定交换机名称与类型
                    key = {"info","error","warning"} // 路由key
            )
    })
    public void receive1(String message){
        System.out.println("message1:"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列
                    exchange = @Exchange(value = "direct_exchange", type = "direct"), // 指定交换机名称与类型
                    key = {"debug"} // 路由key
            )
    })
    public void receive2(String message){
        System.out.println("message2:"+message);
    }

}
