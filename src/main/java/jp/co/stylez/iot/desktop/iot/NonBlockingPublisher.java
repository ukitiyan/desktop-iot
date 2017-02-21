package jp.co.stylez.iot.desktop.iot;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotMqttClient;

import jp.co.stylez.iot.desktop.iot.model.Publisher;

/**
 * NonBlockingPublisher
 * @author y-takahashi
 */
public class NonBlockingPublisher implements Runnable {
	private final Publisher publisher;
    private final AWSIotMqttClient awsIotClient;

    public NonBlockingPublisher(final Publisher publisher, final AWSIotMqttClient awsIotClient) {
        this.publisher = publisher;
        this.awsIotClient = awsIotClient;
    }

    @Override
    public void run() {
        AWSIotMessage message = new NonBlockingPublishListener(this.publisher);
        try {
            awsIotClient.publish(message);
        } catch (AWSIotException e) {
            System.out.println(System.currentTimeMillis() + ": publish failed for " + publisher.getPayload());
        }
    }
}