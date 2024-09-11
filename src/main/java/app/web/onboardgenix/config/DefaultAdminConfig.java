package app.web.onboardgenix.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Data
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "app.default.admin")
public class DefaultAdminConfig {

    String empid;
    String password;
    String name;
    String email;
    String phone;
}
