package jp.co.stylez.iot.desktop.jobs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMessage;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.AWSIotTopic;

import jp.co.stylez.iot.desktop.config.IotProperties;
import jp.co.stylez.iot.desktop.iot.NonBlockingPublisher;
import jp.co.stylez.iot.desktop.iot.NonBlockingWaitPublisher;
import jp.co.stylez.iot.desktop.iot.model.Publisher;

/**
 * HealthCheckPubSubRunner
 * @author y-takahashi
 */
@Component
public class HealthCheckPubSubRunner extends BaseCommandLineRunner {

	private static final AWSIotQos TOPIC_QOS = AWSIotQos.QOS0;

	@Autowired
	private AWSIotMqttClient awsIotClient;
	
	@Autowired
	private IotProperties iotProperties;

	public void run(String... args) throws AWSIotException, InterruptedException {
		// Subscribe shutdown
		Publisher callbackPublisher = new Publisher(this.iotProperties.getTopic().getShutdownNotify(), TOPIC_QOS, this.iotProperties.getPayload().getShutdownNotify());
		AWSIotTopic topic = new ShutdonwTopicListener(this.iotProperties.getTopic().getShutdown(), TOPIC_QOS,
				this.awsIotClient, callbackPublisher, this.iotProperties.getSettings().isShutdown());
		awsIotClient.subscribe(topic, true);

		// Publish health check
		Publisher publisher = new Publisher(this.iotProperties.getTopic().getHealthcheck(), TOPIC_QOS, this.iotProperties.getPayload().getHealthcheck());
		Thread nonBlockingPublishThread = new Thread(new NonBlockingWaitPublisher(publisher, this.awsIotClient, this.iotProperties.getSettings().getHealthcheckWait()));
		nonBlockingPublishThread.start();
		try {
			nonBlockingPublishThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public class ShutdonwTopicListener extends AWSIotTopic {
		private AWSIotMqttClient awsIotClient;
		private Publisher callbackPublisher;
		private boolean isShutdown;

		public ShutdonwTopicListener(String topic, AWSIotQos qos, AWSIotMqttClient awsIotClient, Publisher callbackPublisher, boolean isShutdown) {
			super(topic, qos);
			this.awsIotClient = awsIotClient;
			this.callbackPublisher = callbackPublisher;
			this.isShutdown = isShutdown;
		}

		@Override
        public void onMessage(AWSIotMessage message) {
            System.out.println(System.currentTimeMillis() + ": <<< " + message.getStringPayload());
            
    		// Publish shutdown notify
            Thread nonBlockingPublishThread = new Thread(new NonBlockingPublisher(this.callbackPublisher, awsIotClient));
            nonBlockingPublishThread.start();
            try {
				nonBlockingPublishThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
            if (isShutdown) {
            	String shutdownCmd = "shutdown -s";
            	try {
					@SuppressWarnings("unused")
					Process child = Runtime.getRuntime().exec(shutdownCmd);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
	}

}
