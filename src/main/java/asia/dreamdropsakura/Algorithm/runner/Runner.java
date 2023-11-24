package asia.dreamdropsakura.Algorithm.runner;

import asia.dreamdropsakura.Algorithm.AlgorithmApplication;
import asia.dreamdropsakura.Algorithm.receiver.Receiver;
import org.springframework.amqp.rabbit.connection.RabbitAccessor;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending messages via Command Line Runner...");
        // Notice that the template routes the message to the exchange with a routing key of foo.bar.baz,
        // which matches the binding.
        // In tests, you can mock out the runner so that the receiver can be tested in isolation.
        rabbitTemplate.convertAndSend(AlgorithmApplication.topicExchangeName, "org.spring.test", "Hello " +
                "from Spring");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
