package com.banana;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * https://www.515code.com/
 * bilibili：扎克蕉
 */

@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    // 注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // HelloWorld模型测试
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","hello world");
    }

    // work模型测试
    @Test
    public void testWork(){
        for(int i=1; i<=10; i++){
            rabbitTemplate.convertAndSend("work","work模型"+i);
        }
    }

    // fanout 广播模型测试
    @Test
    public void testFanout(){   // 交换机名 路由Key 消息
        rabbitTemplate.convertAndSend("logs","","Fanout模型发送的消息");
    }

    // route 路由模式
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("direct_exchange","info","发送路由key为info的信息");
    }

    // Topic 动态路由 订阅模式
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topic_exchange","banana.book","banana.book路由信息");
    }

}
