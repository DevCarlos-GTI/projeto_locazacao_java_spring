package io.github.devcarlosgti.localizacao;

import io.github.devcarlosgti.localizacao.domain.entity.Cidade;
import io.github.devcarlosgti.localizacao.service.CidadeService;
import org.hibernate.mapping.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

//	@Autowired
//	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService service;

	@Override
	public void run(String... args) throws Exception {
		//service.listarCidadePorQuantidadeHabitantes();
		//service.listarCidadePorNomelike();
		//service.listarCidadePorNomelikeOrdenacao();
		//service.listarCidadePorNomelikePaginacao();

		//var cidade = new Cidade(null, "Porto Alegre", null);
		//var cidade = new Cidade(null, "Porto Alegre", 127L); //outro teste -> não tem essa quanti
		//var cidade = new Cidade(null, "porto Alegre", null);
		//var cidade = new Cidade(null, "porto", null);
//		var cidade = new Cidade(null, "são", null);
//		service.filtroDinamico(cidade).forEach(System.out::println);
	//	service.listarCidadesByNomeSpecs();

		var cidade = new Cidade(1L, "São Luís", 100L); // busque pelo id, cidade e > 100
		service.listarCidadesSpecsFiltroDinamico(cidade);
	}

	public static void main(String[] args) {

		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
