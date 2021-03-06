package com.banana.fanout;

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
public class FanoutConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列 或者指定@Queue("name")
                    exchange = @Exchange(value = "logs",type = "fanout")// 绑定的交换机
            )
    })
    public void receive1(String message){
        System.out.println("message1:"+ message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 创建临时队列 或者指定@Queue("name")
                    exchange = @Exchange(value = "logs",type = "fanout")// 绑定的交换机
            )
    })
    public void receive2(String message){
        System.out.println("message2:"+ message);
    }
}
