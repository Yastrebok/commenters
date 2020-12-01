package com.fill.commenter.mq;

import com.fill.commenter.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private CommentService commentService;

    @RabbitListener(queues = "${rabbitmq.queue}")

    public void consume(String msg) {
        log.info("Consume: " + msg.substring(0, msg.length() < 250 ? msg.length() : 250));
        commentService.addComment(msg);

    }
}
