package app.web.onboardgenix;

import app.web.onboardgenix.serviceimpl.LoadAppDefaultsServiceImpl;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(value = "app.web.onboardgenix.config")
@Data
@SpringBootApplication
public class OnboardgenixApplication implements CommandLineRunner {

	final LoadAppDefaultsServiceImpl loadAppDefaultsService;

	public static void main(String[] args) {
		SpringApplication.run(OnboardgenixApplication.class, args);
	}

	@Override
	public void run(String... args) {
		loadAppDefaultsService.createAppRoles();
		loadAppDefaultsService.createDefaultAdminUser();
	}
}
