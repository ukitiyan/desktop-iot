package jp.co.stylez.iot.desktop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AwsIotProperties
 * @author y-takahashi
 */
@Component
@ConfigurationProperties(prefix = "aws.iot")
public class AwsIotProperties {
	
	private String clientEndpoint;
	private String clientId;
	private String certificateFile;
	private String privateKeyFile;
	private String thingName;
	
	public String getClientEndpoint() {
		return clientEndpoint;
	}
	public void setClientEndpoint(String clientEndpoint) {
		this.clientEndpoint = clientEndpoint;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getCertificateFile() {
		return certificateFile;
	}
	public void setCertificateFile(String certificateFile) {
		this.certificateFile = certificateFile;
	}
	public String getPrivateKeyFile() {
		return privateKeyFile;
	}
	public void setPrivateKeyFile(String privateKeyFile) {
		this.privateKeyFile = privateKeyFile;
	}
	public String getThingName() {
		return thingName;
	}
	public void setThingName(String thingName) {
		this.thingName = thingName;
	}

}
