package ua.nicety.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "mail")
public record MailProperties(String host,
                             String port,
                             boolean starttls,
                             boolean auth,
                             String fromEmail,
                             String appPassword) {
}
