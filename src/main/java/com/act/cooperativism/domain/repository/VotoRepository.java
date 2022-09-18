package com.act.cooperativism.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.act.cooperativism.domain.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

}
