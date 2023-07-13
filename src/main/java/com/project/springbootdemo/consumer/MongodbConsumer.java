package com.project.springbootdemo.consumer;

import com.project.springbootdemo.dto.User;
import com.project.springbootdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongodbConsumer {
    private final UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User user) {
        userRepository.save(user);
        LOGGER.info(String.format("Received JSON message in Mongo -> %s", user));
    }
}
