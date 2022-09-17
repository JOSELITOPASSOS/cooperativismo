package com.act.cooperativism.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.act.cooperativism.domain.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
