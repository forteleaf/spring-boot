package hello;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class CommandLineBean {

	private final ApplicationArguments args;

	public CommandLineBean(ApplicationArguments args) {
		this.args = args;
	}
	
	@PostConstruct
	public void init() {
		log.info("source {}", List.of(args.getSourceArgs()));
		log.info("optionNames {}", args.getOptionNames());
		Set<String> optionNames = args.getOptionNames();
		for (String optionName : optionNames) {
			log.info("option {} = {}", optionName, args.getOptionValues(optionName));
		}
	}
}
