package ua.nicety.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private int port;

    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;

    @Value("${mail.smtp.auth}")
    private boolean auth;

    @Value("${mail.fromEmail}")
    private String fromEmail;

    @Value("${mail.appPassword}")
    private String appPassword;



    @Bean("emailProperties")
    public Properties emailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable",starttls);
        properties.put("mail.smtp.auth", auth);
        properties.put("fromEmail", fromEmail);
        properties.put("appPassword", appPassword);
        return properties;
    }
}