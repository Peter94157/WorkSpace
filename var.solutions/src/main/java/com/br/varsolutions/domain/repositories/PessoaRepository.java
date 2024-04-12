package com.br.varsolutions.domain.repositories;

import com.br.varsolutions.domain.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {

    PessoaEntity findById(int id);

    void deleteById(int id);
}
