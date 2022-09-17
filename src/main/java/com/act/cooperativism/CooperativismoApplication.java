package com.act.cooperativism;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CooperativismoApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CooperativismoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		System.out.println("\n\n\t\t #####		INICIANDO		#####\n\n");		
	}

}
