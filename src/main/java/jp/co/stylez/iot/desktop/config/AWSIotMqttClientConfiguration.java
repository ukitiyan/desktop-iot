package jp.co.stylez.iot.desktop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;

import jp.co.stylez.iot.desktop.common.AwsIotUtil;
import jp.co.stylez.iot.desktop.common.AwsIotUtil.KeyStorePasswordPair;

/**
 * ServiceConfiguration
 * @author y-takahashi
 */
@Configuration
public class AWSIotMqttClientConfiguration {

	@Bean
	protected AWSIotMqttClient awsIotClient(AwsIotProperties awsIotProperties) throws AWSIotException {
		KeyStorePasswordPair pair = AwsIotUtil.getKeyStorePasswordPair(awsIotProperties.getCertificateFile(),
				awsIotProperties.getPrivateKeyFile());
		AWSIotMqttClient awsIotClient = new AWSIotMqttClient(awsIotProperties.getClientEndpoint(),
				awsIotProperties.getClientId(), pair.keyStore, pair.keyPassword);
		awsIotClient.connect();
		return awsIotClient;
	}

}
