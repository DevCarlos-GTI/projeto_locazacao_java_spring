package io.github.devcarlosgti.localizacao;

import io.github.devcarlosgti.localizacao.domain.entity.Cidade;
import io.github.devcarlosgti.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		//listarCidade();
		//listarCidadePorNome();
		//listarCidadePorHabitantes();
		//listarCidadePorNomeComeçandoPor();
		//listarCidadePorNomeTerminandoPor();
		//listarCidadePorNomeContendo();
		//listarCidadePorNomelike();
		//listarCidadePorMenorHabitantes();
		//listarCidadePorMenorOuIgualHabitantes();
		//listarCidadePorMaiorHabitantes();
		listarCidadePorMenorHabitantesENome();
	}
	void listarCidadePorNome(){
		cidadeRepository.findByNome("Salvador").forEach(System.out::println);
	}

	void listarCidadePorNomelike(){
		//cidadeRepository.findByNomeLike("%a%").forEach(System.out::println); //contem
		//cidadeRepository.findByNomeLike("%ro").forEach(System.out::println); //termina com
		//cidadeRepository.findByNomeLike("São%").forEach(System.out::println); //começa com
		cidadeRepository.findByNomeLike("são%").forEach(System.out::println); //começa com/convert minu em Maiusc
	}

//	void listarCidadePorNomeComeçandoPor(){
//		cidadeRepository.findByNomeStartingWith("São").forEach(System.out::println);
//	}
//
//	void listarCidadePorNomeTerminandoPor(){
//		//cidadeRepository.findByNomeEndingWith("s").forEach(System.out::println);
//		cidadeRepository.findByNomeEndingWith("za").forEach(System.out::println);
//	}
//
//	void listarCidadePorNomeContendo(){
//		//cidadeRepository.findByNomeEndingWith("s").forEach(System.out::println);
//		cidadeRepository.findByNomeContaining("o").forEach(System.out::println);
//	}

	void listarCidadePorHabitantes(){

		cidadeRepository.findByHabitantes(1779000L).forEach(System.out::println);
	}

	void listarCidadePorMenorHabitantes(){
		cidadeRepository.findByHabitantesLessThan(1000000L).forEach(System.out::println);
	}

	void listarCidadePorMenorOuIgualHabitantes(){
		cidadeRepository.findByHabitantesLessThanEqual(1000000L).forEach(System.out::println);
	}

	void listarCidadePorMenorHabitantesENome(){
		cidadeRepository.findByHabitantesLessThanAndNomeLike(1000001L,"Br%").forEach(System.out::println);
	}

	void listarCidadePorMaiorHabitantes(){
		cidadeRepository.findByHabitantesGreaterThan(1000000L).forEach(System.out::println);
	}

	//testando cidade no DB
	@Transactional // transação de DB
	void salvarCidade(){
		var cidade = new Cidade(1L, "São Luís", 1779000L);
		cidadeRepository.save(cidade);
	}

	void listarCidade(){
		cidadeRepository.findAll().forEach(System.out::println);
	}
	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
