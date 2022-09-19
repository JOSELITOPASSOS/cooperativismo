package com.act.cooperativism.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.act.cooperativism.domain.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
	@Query(value = "SELECT COUNT(1) QTD FROM VOTO V " +
				   "INNER JOIN SESSAO_VOTACAO SV ON V.SESSAO_ID = SV.ID " +
				   "INNER JOIN PAUTA P ON SV.PAUTA_ID = P.ID " +
				   "WHERE ASSOCIADO = :associadoId AND P.ID = :pautaId",
		   nativeQuery = true)
	public Integer verificarSeVotou(@Param("associadoId") Long associadoId, @Param("pautaId") Long pautaId);

	public List<Voto> findBySessaoVotacaoPautaId(Long pautaId);
}
