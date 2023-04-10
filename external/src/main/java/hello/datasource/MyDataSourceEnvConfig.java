package hello.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.List;

@Configuration
@Import(MyDataSourceEnvConfig.class)
public class MyDataSourceEnvConfig {

	private final Environment env;

	public MyDataSourceEnvConfig(Environment env) {
		this.env = env;
	}

	@Bean
	public MyDataSource myDataSource() {
		String   url           = env.getProperty("my.datasource.url");
		String   username      = env.getProperty("my.datasource.username");
		String   password      = env.getProperty("my.datasource.password");
		int      maxConnection = env.getProperty("my.datasource.etc.max-connection", Integer.class);
		Duration timeout       = env.getProperty("my.datasource.etc.timeout", Duration.class);
		List     options       = env.getProperty("my.datasource.etc.options", List.class);

		return new MyDataSource(url, username, password, maxConnection, timeout, options);
	}
}
