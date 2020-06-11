package id.learn.demoreactor;

import id.learn.demoreactor.entity.Book;
import id.learn.demoreactor.entity.Customer;
import id.learn.demoreactor.repository.BookRepository;
import id.learn.demoreactor.repository.ReactiveCustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class DemoReactorApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ReactiveCustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start data initialization  ...");
		Book b1 = Book.builder()
				.title("BCI")
				.author("Gyle Maxwell")
				.isbn("1234-123-445-56").build();

		Book b2 = Book.builder()
				.title("EIP")
				.author("Adnan Aziz")
				.isbn("1576-33-44-56").build();

		Book b3 = Book.builder()
				.title("Algorithms")
				.author("R Sedwig")
				.isbn("1567-34-56-56").build();

		this.bookRepository
				.deleteAll()
				.thenMany(
						Flux.just(b1, b2, b3)
								.flatMap(this.bookRepository::save)
				)
				.log()
				.subscribe(
						null,
						null,
						() -> log.info("done initialization...")
				);

		Customer c1 = Customer.builder()
				.name("App")
				.age(2)
				.active(true)
				.build();

		Customer c2 = Customer.builder()
				.name("Abbu")
				.age(2)
				.active(false)
				.build();

		Customer c3 = Customer.builder()
				.name("Una")
				.age(2)
				.active(true)
				.build();

		this.customerRepository
				.deleteAll()
				.thenMany(
						Flux.just(c1, c2, c3)
						.flatMap(this.customerRepository::save)
				)
				.log()
				.subscribe(
						null,
						null,
						() -> log.info("done initialization...")
				);
	}
}
