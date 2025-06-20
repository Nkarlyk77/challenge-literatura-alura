package com.literalura.challenge;

import com.literalura.challenge.principal.PrincipalMenu;
import com.literalura.challenge.repository.AuthorRepository;
import com.literalura.challenge.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraChallengeApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	public static void main(String[] args) {

		SpringApplication.run(LiteraluraChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PrincipalMenu menu = new PrincipalMenu(bookRepository, authorRepository);
		menu.showMenu();
	}
}
