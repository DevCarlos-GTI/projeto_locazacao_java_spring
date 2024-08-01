package io.github.devcarlosgti.localizacao.domain.repository.specs;

import io.github.devcarlosgti.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

//Classe para fazer comparações
public abstract class CidadeSpecs { //abstract pq ñ inirei instanciar

    public static Specification<Cidade> propertyEqual(String prop, Object value){
        return (root, query, cb) -> cb.equal( root.get(prop), value); //comparação por valor
    }

    public static Specification<Cidade> idEqual(Long id){
        return (root, query, cb) -> cb.equal( root.get("id"), id); //comparação por id
    }

    public static Specification<Cidade> nomeEqual(String nome){
        return (root, query, cb) -> cb.equal( root.get("nome"), nome); //comparação por nome
    }

    public static Specification<Cidade> habitantesGreaterThan(Long value){ //GreaterThan(ou gt) -> maior que
        return (root, query, cb) -> cb.greaterThan( root.get("habitantes"), value); ////comparação habitantes > o valor
    }

    public static Specification<Cidade> habitantesBetween(Long min, Long max){ //between -> entre
        return (root, query, cb) -> cb.between( root.get("habitantes"), min,max ); ////comparação entre min e max
    }

    public static Specification<Cidade> nomeLike(String nome){
        return (root, query, cb) -> cb.like( cb.upper(root.get("nome")), "%" + nome + "%" .toUpperCase(Locale.ROOT));
    }
}
