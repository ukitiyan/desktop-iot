package jp.co.stylez.iot.desktop.iot.model;

import com.amazonaws.services.iot.client.AWSIotQos;

/**
 * Publisher Model
 * @author y-takahashi
 */
public class Publisher {

	String topic;
	AWSIotQos qos;
	String payload;
	
	public Publisher() {
	}

	public Publisher(String topic, AWSIotQos qos, String payload) {
		this.topic = topic;
		this.qos = qos;
		this.payload = payload;
	}

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public AWSIotQos getQos() {
		return qos;
	}
	public void setQos(AWSIotQos qos) {
		this.qos = qos;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
}
