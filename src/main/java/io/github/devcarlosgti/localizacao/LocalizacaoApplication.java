package io.github.devcarlosgti.localizacao;

import io.github.devcarlosgti.localizacao.service.CidadeService;
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
		service.listarCidadePorNomelikePaginacao();
	}

	public static void main(String[] args) {

		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
