package com.act.cooperativism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.act.cooperativism.domain.entity.SessaoVotacao;

@Repository
public interface SessaoVotacaoRepository  extends JpaRepository<SessaoVotacao, Long>{

}
