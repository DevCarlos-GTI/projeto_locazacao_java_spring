package io.github.devcarlosgti.localizacao.service;

import io.github.devcarlosgti.localizacao.domain.entity.Cidade;
import io.github.devcarlosgti.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.github.devcarlosgti.localizacao.domain.repository.specs.CidadeSpecs.*;

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

    public List<Cidade> filtroDinamico(Cidade cidade){
        //para não precisar fazer varis ifs para testar se é null ou ñ usa a interface Example
        ExampleMatcher matcher = ExampleMatcher.
                matching()
                .withIgnoreCase() //ignora caixa baixa ou alta
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING) // traz ja com inicio da palavra
//                .withIgnoreNullValues() //ignora valores nulls
                ;
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
//       return repository.findByHabitantesLessThanAndNomeLike(cidade.getHabitantes(), cidade.getNome());
    }

    public void listarCidadesByNomeSpecs(){
        repository
                //.findAll(nomeEqual("São Paulo").and(habitantesGreaterThan(1000)))
                //.findAll(nomeEqual("São Paulo").or(habitantesGreaterThan(1000))) //outro teste
                //.findAll(propertyEqual("nome","São Paulo").or(propertyEqual("habitantes", 12330000))) //outro teste
                .findAll(nomeEqual("São Paulo").or(idEqual(4L))) //outro teste
                .forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where(((root, query, cb) -> cb.conjunction() ));
        // resumindo -> select * from cidade where(onde) 1 = 1;

        if(filtro.getId() != null){
            specs = specs.and(idEqual(filtro.getId())); // se ñ for nul, ele recebe esse id filtrado
        }

        if(StringUtils.hasText(filtro.getNome())){ // se existe algum texto, ou seja, ñ esteja vazio
            specs = specs.and(nomeLike(filtro.getNome())); // pega esse nome
        }

        if(filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }
        repository.findAll(specs).forEach(System.out::println); // se td dê certo imprimo
    }
}
