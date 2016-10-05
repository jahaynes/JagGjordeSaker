package se.mirado.jgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	@Autowired
	public Main(AppReactor appReactor) {
		appReactor.start();
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
