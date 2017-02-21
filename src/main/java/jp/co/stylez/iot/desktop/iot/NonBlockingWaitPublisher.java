package jp.co.stylez.iot.desktop.iot;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotMqttClient;

import jp.co.stylez.iot.desktop.iot.model.Publisher;

/**
 * NonBlockingWaitPublisher
 * @author y-takahashi
 */
public class NonBlockingWaitPublisher implements Runnable {
    private final Publisher publisher;
    private final AWSIotMqttClient awsIotClient;
    private final int waitSecond;

    public NonBlockingWaitPublisher(final Publisher publisher, final AWSIotMqttClient awsIotClient, final int waitSecond) {
        this.publisher = publisher;
        this.awsIotClient = awsIotClient;
        this.waitSecond = waitSecond;
    }

    @Override
    public void run() {
        AWSIotMessage message = new NonBlockingPublishListener(this.publisher);
        while (true) {
	        try {
	            awsIotClient.publish(message);
	        } catch (AWSIotException e) {
	            System.out.println(System.currentTimeMillis() + ": publish failed for " + this.publisher.getPayload());
	        }
	
	        try {
	            Thread.sleep(this.waitSecond * 1000);
	        } catch (InterruptedException e) {
	            System.out.println(System.currentTimeMillis() + ": NonBlockingPublisher was interrupted");
	            return;
	        }
	    }

    }
}