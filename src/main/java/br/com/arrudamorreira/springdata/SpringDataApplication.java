package br.com.arrudamorreira.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.arrudamorreira.springdata.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner{
	
	private Boolean system = true;
	
	private final CrudCargoService crudCargoService;
	
	public SpringDataApplication(CrudCargoService crudCargoService) {
		this.crudCargoService = crudCargoService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while (system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			
			int action = scanner.nextInt();
			if(action == 1) {
				crudCargoService.inicial(scanner);
			}else {
				system = false;
			}
		}
	}
}
