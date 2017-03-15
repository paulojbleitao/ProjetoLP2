package gerenciador;

import java.time.*;

import factory.FactoryParticipacao;
import participacao.Participacao;
import participacao.Professor;
import pessoa.Pessoa;
import projeto.Monitoria;
import projeto.Projeto;

public class GerenciadorParticipacao {

	private FactoryParticipacao factoryParticipacao;
	
	public void associaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) throws Exception {
		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}
		if (valorHora <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		Participacao participacao = factoryParticipacao.criaGraduando(pessoa, projeto, projeto.getDataInicio(), projeto.getDuracao(), qntHoras, valorHora, "GRADUANDO");
		pessoa.addParticipacao(participacao);
		projeto.addParticipacao(participacao);
	}
	
	public void associaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora, int qntHoras) throws Exception {
		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}
		if (valorHora <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		if (projeto instanceof Monitoria){
			if (((Monitoria) projeto).contemProfessor()) {
				throw new Exception("Erro na associacao de pessoa a projeto: Monitoria nao pode ter mais de um professor");
			}
		}
		Participacao participacao = factoryParticipacao.criaProfessor(pessoa, projeto, projeto.getDataInicio(), projeto.getDuracao(), qntHoras, valorHora, coordenador);
		pessoa.addParticipacao(participacao);
		projeto.addParticipacao(participacao);
	}
	
	public void associaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras) throws Exception {
		
		
	}
	
	
	
	
	
	
	
	

}
