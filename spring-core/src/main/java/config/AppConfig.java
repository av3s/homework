package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("mbank")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
