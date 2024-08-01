package io.github.devcarlosgti.localizacao.domain.repository;

import io.github.devcarlosgti.localizacao.domain.entity.Cidade;
import io.github.devcarlosgti.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    //busca
    @Query(nativeQuery = true, value = "select * from tb_cidade as c where c.nome =:nome ") // ele interpreta como sql nativo
    List<Cidade> findByNomeSqlNativo(@Param("nome") String nome);

    //busca pelo nome da cidade
    @Query(nativeQuery = true, value = "select c.nome from tb_cidade as c where c.nome =:nome ") // ele interpreta como sql nativo
    List<String> findByNomeSqlNativoPorNomeCidade(@Param("nome") String nome);

    //busca pelo id
    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome =:nome ") // ele interpreta como sql nativo
    List<CidadeProjection> findByNomeSqlNativoPorNomeIdCidade(@Param("nome") String nome); // as id para sobrecrever p id/ pq na tabela é id_cidade ja aqui na entidade é apenas id

    //busca pelo nome correto
    List<Cidade> findByNome(String nome);

    //buscar por nome
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ") // interpreta como hql
    List<Cidade> findByNomeLike(String nome);

    //buscar por nome like ordenada(sort para ordenação)
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLikeSort(String nome, Sort sort);

    //buscar por nome like paginada(pageable para paginação)
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    Page<Cidade> findByNomeLikePage(String nome, Pageable pageable);

    //Lower - converte todas as letras maiúsculas em minúsculas.
    //Upper - converte todas as letras minúsculas em maiúsculas.
    //Proper - converte a primeira letra de cada palavra em maiúscula se for minúscula,
    // e converte todas as outras letras maiúsculas em minúsculas.

//    //busca pelo nome começando por aquele pedaço
//    List<Cidade> findByNomeStartingWith(String nome);
//
//    //busca pelo nome terminando por aquele pedaço
//    List<Cidade> findByNomeEndingWith(String nome);
//
//    //busca pelo nome contendo aquele pedaço
//    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);

}
