package jp.co.stylez.iot.desktop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * IotProperties
 * @author y-takahashi
 */
@Component
@ConfigurationProperties(prefix = "iot")
public class IotProperties {

	private Topic topic;
	private Payload payload;
	private Settings settings;
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}



	public static class Topic {

		private String healthcheck;
		private String shutdown;
		private String shutdownNotify;
		
		public String getHealthcheck() {
			return healthcheck;
		}
		public void setHealthcheck(String healthcheck) {
			this.healthcheck = healthcheck;
		}
		public String getShutdown() {
			return shutdown;
		}
		public void setShutdown(String shutdown) {
			this.shutdown = shutdown;
		}
		public String getShutdownNotify() {
			return shutdownNotify;
		}
		public void setShutdownNotify(String shutdownNotify) {
			this.shutdownNotify = shutdownNotify;
		}

	}

	public static class Payload {
		
		private String healthcheck;
		private String shutdownNotify;
		
		public String getHealthcheck() {
			return healthcheck;
		}
		public void setHealthcheck(String healthcheck) {
			this.healthcheck = healthcheck;
		}
		public String getShutdownNotify() {
			return shutdownNotify;
		}
		public void setShutdownNotify(String shutdownNotify) {
			this.shutdownNotify = shutdownNotify;
		}

	}
	
	public static class Settings {

		private int healthcheckWait;
		private boolean shutdown;
		
		public int getHealthcheckWait() {
			return healthcheckWait;
		}
		public void setHealthcheckWait(int healthcheckWait) {
			this.healthcheckWait = healthcheckWait;
		}
		public boolean isShutdown() {
			return shutdown;
		}
		public void setShutdown(boolean shutdown) {
			this.shutdown = shutdown;
		}

	}


}
