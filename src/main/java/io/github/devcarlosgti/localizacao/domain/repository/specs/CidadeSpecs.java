package io.github.devcarlosgti.localizacao.domain.repository.specs;

import io.github.devcarlosgti.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs { //abstract pq ñ inirei instanciar

    public static Specification<Cidade> propertyEqual(String prop, Object value){
        return (root, query, cb) -> cb.equal( root.get(prop), value); //comparação por nome
    }

    public static Specification<Cidade> idEqual(Long id){
        return (root, query, cb) -> cb.equal( root.get("id"), id); //comparação por nome
    }

    public static Specification<Cidade> nomeEqual(String nome){
        return (root, query, cb) -> cb.equal( root.get("nome"), nome); //comparação por nome
    }

    public static Specification<Cidade> habitantesGreaterThan(Integer value){ //GreaterThan -> maior que
        return (root, query, cb) -> cb.greaterThan( root.get("habitantes"), value); ////comparação habitantes > o valor
    }
}
