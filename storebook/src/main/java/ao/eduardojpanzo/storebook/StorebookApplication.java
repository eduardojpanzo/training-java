package ao.eduardojpanzo.storebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ao.eduardojpanzo.storebook.main.Main;
import ao.eduardojpanzo.storebook.repository.BookRepository;

@SpringBootApplication
public class StorebookApplication  implements CommandLineRunner{
	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StorebookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);

		main.show();
	}

}
