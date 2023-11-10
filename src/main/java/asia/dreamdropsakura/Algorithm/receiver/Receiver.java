package asia.dreamdropsakura.Algorithm.receiver;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * With any messaging-based application, you need to create a receiver that responds to published messages.
 * The Receiver is a POJO that defines a method for receiving messages.
 * When you register it to receive messages, you can name it anything you want.
 *
 */
@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
