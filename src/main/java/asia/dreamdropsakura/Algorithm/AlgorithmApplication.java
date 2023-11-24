package asia.dreamdropsakura.Algorithm;

import asia.dreamdropsakura.Algorithm.receiver.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * https://spring.io/guides/gs/messaging-rabbitmq/
 *
 */
@SpringBootApplication
public class AlgorithmApplication {

	public static final String topicExchangeName = "spring-boot-exchange";

	static final String queueName = "spring-boot";

	/**
	 * This method creates an AMQP queue.
	 *
	 * @return
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	/**
	 * This method creates a topic exchange
	 *
	 * @return
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	/**
	 * This binding method binds Queue and Exchange together,
	 * defining the behavior that occurs when RabbitTemplate publishes to an exchange.
	 *
	 * Be notice that Spring AMQP requires that the Queue, the TopicExchange, and the Binding be declared as top-level
	 * Spring beans in order to be set up properly.
	 *
	 * @param queue
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("org.spring.#");
	}

	/**
	 *  let MessageListenerAdapter bean to registered as a message listener in the container
	 * It listens for messages on the spring-boot queue. Because the Receiver class is a POJO,
	 * it needs to be wrapped in the MessageListenerAdapter, where you specify that it invokes receiveMessage
	 * @param connectionFactory
	 * @param messageListenerAdapter
	 * @return
	 */
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}

	/**
	 * This method is registered as a message listener in the container (defined in container()).
	 * It listens for messages on the spring-boot queue
	 *
	 * @param receiver
	 * @return
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		// 将在收到消息后调用入参的Receiver 类中的 asia.dreamdropsakura.Algorithm.receiver.receiveMessage 方法
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public static void main(String[] args) {
		SpringApplication.run(AlgorithmApplication.class, args).close();
	}

}
