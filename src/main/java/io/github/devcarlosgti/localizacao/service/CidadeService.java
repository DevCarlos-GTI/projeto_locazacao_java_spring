package io.github.devcarlosgti.localizacao.service;

import io.github.devcarlosgti.localizacao.domain.entity.Cidade;
import io.github.devcarlosgti.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    //testando cidade no DB -> transação de DB
    @Transactional
    public void salvarCidade(){
        var cidade = new Cidade(1L, "São Luis", 1779000L);
        repository.save(cidade);
    }

    public void listarCidadePorNome(){
        repository.findByNome("Salvador").forEach(System.out::println);
    }

    public void listarCidadePorNomelike(){
        //cidadeRepository.findByNomeLike("%a%").forEach(System.out::println); //contem
        //cidadeRepository.findByNomeLike("%ro").forEach(System.out::println); //termina com
        //cidadeRepository.findByNomeLike("São%").forEach(System.out::println); //começa com
        repository.findByNomeLike("São%").forEach(System.out::println); //começa com/convert minu em Maiusc
    }
    public void listarCidadePorNomelikeOrdenacao(){
        repository.findByNomeLikeSort("São%", Sort.by("habitantes")).forEach(System.out::println); //começa com/convert minu em Maiusc
    }

    public void listarCidadePorNomelikePaginacao(){
        //Pageable pageable = PageRequest.of(0,10); // 10 regitros
        //Pageable pageable = PageRequest.of(0,2);
        //Pageable pageable = PageRequest.of(1,2); // 2 em 2 p cada pagina
        Pageable pageable = PageRequest.of(2,2); // 2 em 2 p cada pagina
        repository.findByNomeLikePage("%%%%", pageable) // %%%% é p trazer todos registros
                .forEach(System.out::println); //começa com/convert minu em Maiusc
    }
//	public void listarCidadePorNomeComeçandoPor(){
//		cidadeRepository.findByNomeStartingWith("São").forEach(System.out::println);
//	}
//
//	public void listarCidadePorNomeTerminandoPor(){
//		//cidadeRepository.findByNomeEndingWith("s").forEach(System.out::println);
//		cidadeRepository.findByNomeEndingWith("za").forEach(System.out::println);
//	}
//
//	public void listarCidadePorNomeContendo(){
//		//cidadeRepository.findByNomeEndingWith("s").forEach(System.out::println);
//		cidadeRepository.findByNomeContaining("o").forEach(System.out::println);
//	}

    public void listarCidadePorQuantidadeHabitantes(){

        repository.findByHabitantes(1779000L).forEach(System.out::println);
    }

    public void listarCidadePorMenorHabitantes(){
        repository.findByHabitantesLessThan(1000000L).forEach(System.out::println);
    }

    public void listarCidadePorMenorOuIgualHabitantes(){
        repository.findByHabitantesLessThanEqual(1000000L).forEach(System.out::println);
    }

    public void listarCidadePorMenorHabitantesENome(){
        repository.findByHabitantesLessThanAndNomeLike(1000001L,"Br%").forEach(System.out::println);
    }

    public void listarCidadePorMaiorHabitantes(){
        repository.findByHabitantesGreaterThan(1000000L).forEach(System.out::println);
    }

    public void listarCidade(){
        repository.findAll().forEach(System.out::println);
    }
}
