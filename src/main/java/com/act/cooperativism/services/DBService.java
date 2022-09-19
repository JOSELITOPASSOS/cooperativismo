package com.act.cooperativism.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Associado;
import com.act.cooperativism.domain.entity.Pauta;
import com.act.cooperativism.domain.entity.SessaoVotacao;
import com.act.cooperativism.domain.entity.Voto;
import com.act.cooperativism.domain.entity.VotoEnum;

@Service
public class DBService {

	private static final Logger LOG = LoggerFactory.getLogger(DBService.class);

	@Autowired
	private PautaService pautaService; 

	@Autowired
	private SessaoVotacaoService sessaoService;

	@Autowired
	private AssociadoService associadoService;
	
	@Autowired
	private VotoService votoService;

	public void instanciaBaseDeDados() throws Exception {
		LOG.info("Criando modeo de teste");

		var pauta = novaPauta();
		pautaService.cadastrar(pauta);

		var sessao01 = novaSessao(pauta);
		sessaoService.abrir(sessao01);
		
		var sessao02 = novaSessao(pauta);
		sessaoService.abrir(sessao02);

		var teste01 = novoAssociado("19839091069", "Associado Teste 01");
		associadoService.cadastrar(teste01);
		
		var teste02 = novoAssociado("55674527059", "Associado Teste 02");
		associadoService.cadastrar(teste02);
		
		var teste03 = novoAssociado("87410177063", "Associado Teste 03");
		associadoService.cadastrar(teste03);
		
		var teste04 = novoAssociado("83695835044", "Associado Teste 04");
		associadoService.cadastrar(teste04);
		
		var teste05 = novoAssociado("90988143011", "Associado Teste 05");
		associadoService.cadastrar(teste05);
		
		var teste06 = novoAssociado("14228001050", "Associado Teste 06");
		associadoService.cadastrar(teste06);
		
		var teste07 = novoAssociado("15567522029", "Associado Teste 07");
		associadoService.cadastrar(teste07);
		
		var teste08 = novoAssociado("67021756046", "Associado Teste 08");
		associadoService.cadastrar(teste08);
		
		var teste09 = novoAssociado("71254816046", "Associado Teste 09");
		associadoService.cadastrar(teste09);
		
		var teste10 = novoAssociado("40470301040", "Associado Teste 10");
		associadoService.cadastrar(teste10);
		
		var voto01 = novoVoto(sessao01, teste01, VotoEnum.SIM);
		var voto02 = novoVoto(sessao01, teste02, VotoEnum.SIM);
		var voto03 = novoVoto(sessao02, teste03, VotoEnum.NAO);
		var voto04 = novoVoto(sessao01, teste04, VotoEnum.SIM);
		var voto05 = novoVoto(sessao01, teste05, VotoEnum.SIM);
		var voto06 = novoVoto(sessao01, teste06, VotoEnum.SIM);
		var voto07 = novoVoto(sessao02, teste07, VotoEnum.NAO);
		var voto08 = novoVoto(sessao02, teste07, VotoEnum.SIM);
		var voto09 = novoVoto(sessao01, teste09, VotoEnum.NAO);
		var voto10 = novoVoto(sessao01, teste10, VotoEnum.SIM);
		
		List<Voto> votos = Arrays.asList(voto01, voto02, voto03, voto04, voto05, voto06, voto07, voto08, voto09, voto10);
		votoService.registrarEmlote(votos);
		
	}

	private Voto novoVoto(SessaoVotacao sessao, Associado associado, VotoEnum opcao) {
		var voto10 = Voto.builder()
				.opcao(opcao.getOpcao())
				.associado(associado)
				.sessaoVotacao(sessao)
				.build();
		return voto10;
	}

	private Associado novoAssociado(String cpf, String nome) {
		var associado = Associado.builder()
				.nome(nome)
				.cpf(cpf)
				.build();
		return associado;
	}

	private SessaoVotacao novaSessao(Pauta pauta) {
		var sessao = SessaoVotacao.builder()
				.dataInicio(LocalDateTime.now().plusHours(2))
				.dataFim(LocalDateTime.now())
				.duracao(Duration.parse("PT30M"))
				.pauta(pauta)
				.build();
		return sessao;
	}

	private Pauta novaPauta() {
		var pauta = Pauta.builder().descricao("Teste de votação")
				.data(LocalDateTime.now())
//				.aprovada(true)
				.build();
		return pauta;
	}

}
