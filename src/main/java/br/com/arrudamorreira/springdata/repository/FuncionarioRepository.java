package br.com.arrudamorreira.springdata.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.arrudamorreira.springdata.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{
	
	List<Funcionario> findByNome(String nome);
	
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);
	
	@Query("SELECT f FROM Funcionario f "
			+ "WHERE f.nome = :nome "
			+ "AND f.salario >= :salario "
			+ "AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	
	List<Funcionario> findByUnidades_Descricao(String descricao);
	
	@Query("SELECT f FROM Funcionario f "
			+ "JOIN f.unidades u "
			+ "WHERE u.descricao = :descricao")
	List<Funcionario> findByUnidadePelaDescricao(String descricao);
	
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
}
