package br.com.arrudamorreira.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.arrudamorreira.springdata.orm.Funcionario;
import br.com.arrudamorreira.springdata.orm.FuncionarioProjecao;
import br.com.arrudamorreira.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	private Boolean system = true;

	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual acao de funcionario deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario Nome");
			System.out.println("2 - Busca funcionario por Nome, data contratacao e salario maior");
			System.out.println("3 - Busca funcionario por data contratacao");
			System.out.println("4 - Exibe funcionario por Id, Nome, Data");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacaoEmDiante(scanner);
				break;
			case 4:
				pesquisafuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}

		}
	}

	private void buscaFuncionarioNome(Scanner scanner) {

		System.out.println("Digite o nome do Funcionario: ");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);

		System.out.println("- Fim -");
		System.out.println("");
	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {

		System.out.println("Qual nome ? ");
		String nome = scanner.next();

		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		
		System.out.println("Digite a data de contracao(dd/mm/yyyy): ");
		String dataContratacao = scanner.next();
		LocalDate data = LocalDate.parse(dataContratacao, formatter);
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, data);
		list.forEach(System.out::println);
		
		System.out.println("- Fim -");
		System.out.println("");
	}
	
	private void buscaFuncionarioDataContratacaoEmDiante(Scanner scanner) {

		System.out.println("Digite a data desejada(dd/mm/yyyy): ");
		String dataContratacao = scanner.next();
		LocalDate data = LocalDate.parse(dataContratacao, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(data);
		list.forEach(System.out::println);
		
		System.out.println("- Fim -");
		System.out.println("");
	}
	
	private void pesquisafuncionarioSalario() {
		List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
		lista.forEach(f -> System.out.println("Funcionario: | id: " + f.getId() + " | Nome: " + f.getNome() + " | Salario: " + f.getSalario()));
	}
}
