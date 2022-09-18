package com.act.cooperativism.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.act.cooperativism.domain.entity.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

}
