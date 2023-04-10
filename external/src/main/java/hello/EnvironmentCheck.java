package hello;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnvironmentCheck {

	private final Environment environment;

	public EnvironmentCheck(Environment environment) {
		this.environment = environment;
	}

	@PostConstruct
	public void init() {
		String url = environment.getProperty("url");
		String username = environment.getProperty("username");
		String password = environment.getProperty("password");
		log.info("url {}", url);
		log.info("username {}", username);
		log.info("password {}", password);

		String[] activeProfiles = environment.getActiveProfiles();
		log.info("{}" , environment.getActiveProfiles());
		log.info("{}" , environment.getDefaultProfiles());
		log.info("{}" , environment.getProperty("spring.profiles.active"));
	}
}
