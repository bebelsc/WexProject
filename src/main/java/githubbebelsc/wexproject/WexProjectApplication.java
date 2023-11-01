package githubbebelsc.wexproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "githubbebelsc.wexproject.model")
public class WexProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WexProjectApplication.class, args);
	}

}
