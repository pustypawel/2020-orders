package pl.edu.wszib.orderinfrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

//@ConfigurationPropertiesBinding
//@ConfigurationPropertiesScan
@Configuration
@ConfigurationProperties(prefix = "testconfig")
@Data
public class MyConfig {
    private String myProperty1;
    private String myProperty2;
}
