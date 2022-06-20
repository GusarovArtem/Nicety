package ua.nicety.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@RequiredArgsConstructor
@Configuration
public class MailConfig {

   private final MailProperties mailProperties;


    @Bean("emailProperties")
    public Properties emailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailProperties.host());
        properties.put("mail.smtp.port", mailProperties.port());
        properties.put("mail.smtp.starttls.enable",mailProperties.starttls());
        properties.put("mail.smtp.auth", mailProperties.auth());
        properties.put("fromEmail", mailProperties.fromEmail());
        properties.put("appPassword", mailProperties.appPassword());
        return properties;
    }
}