package com.alecrim.repository;

import java.util.List;
import com.alecrim.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

    List<Pessoa> findByTipo(String tipo);

}