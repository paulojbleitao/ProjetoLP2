package gerenciador;

import java.time.LocalDate;
import java.util.Map;

import participacao.FactoryParticipacao;
import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;

public class GerenciadorParticipacoes {
	
	Map<Pessoa, Projeto> Participacoes;
	Map<Projeto, Pessoa> Participantes;
	FactoryParticipacao factoryParticipacao;
	GerenciadorPessoa gerenPessoa;
	GerenciadorProjeto gerenProjeto;
		
	public void associaProfessor (String cpf, String codigo , LocalDate dataInicio, int horasSemanais, double valorHora, boolean coordenador) throws Exception {
		Participacao prof;
		Projeto projeto = gerenProjeto.buscaProjeto(codigo);
		prof = factoryParticipacao.criaProfessor(gerenPessoa.buscaPessoa(cpf), projeto, dataInicio, projeto.getDuracao(), horasSemanais, valorHora, coordenador);
		
		
		
		}
	
	}

