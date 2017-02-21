package jp.co.stylez.iot.desktop.iot;

import com.amazonaws.services.iot.client.AWSIotMessage;

import jp.co.stylez.iot.desktop.iot.model.Publisher;

/**
 * This class extends {@link AWSIotMessage} to provide customized handlers for
 * non-blocking message publishing.
 */
public class NonBlockingPublishListener extends AWSIotMessage {

    public NonBlockingPublishListener(final Publisher publisher) {
        super(publisher.getTopic(), publisher.getQos(), publisher.getPayload());
    }

    @Override
    public void onSuccess() {
        System.out.println(System.currentTimeMillis() + ": >>> " + getStringPayload());
    }

    @Override
    public void onFailure() {
        System.out.println(System.currentTimeMillis() + ": publish failed for " + getStringPayload());
    }

    @Override
    public void onTimeout() {
        System.out.println(System.currentTimeMillis() + ": publish timeout for " + getStringPayload());
    }

}
