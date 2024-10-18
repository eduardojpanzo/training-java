package ao.eduardojpanzo.storebook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ao.eduardojpanzo.storebook.main.Main;

@SpringBootApplication
public class StorebookApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StorebookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();

		main.show();
	}

}
