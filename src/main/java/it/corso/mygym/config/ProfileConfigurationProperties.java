package it.corso.mygym.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("example")
public class ProfileConfigurationProperties {

    private String title = "MyGym Default Service";
    private String text = "default";
}
