package factory;

import java.time.*;

import participacao.PosGraduando;
import participacao.Graduando;
import participacao.Participacao;
import participacao.Professor;
import participacao.Profissional;
import participacao.TipoPosGraduando;
import participacao.TipoProfissional;
import pessoa.Pessoa;
import projeto.Projeto;

public class FactoryParticipacao {

	public Participacao criaGraduando(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao,
			int horasSemanais, double valorHora) {
		Participacao graduando = new Graduando(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);
		return graduando;
	}

	public Participacao criaPosGraduando(Pessoa pessoa, Projeto projeto, String nivel, LocalDate dataInicio, int duracao,
			int horasSemanais, double valorHora) throws Exception {
		TipoPosGraduando tipo = null;
		switch(nivel.toUpperCase()) {
			case "MESTRADO":
				tipo = TipoPosGraduando.MESTRADO;
				break;
			case "DOUTORADO":
				tipo = TipoPosGraduando.DOUTORADO;
				break;
			default:
				throw new Exception("Erro na associacao de pessoa a projeto: Tipo de pos graduando invalido");
		}
		Participacao posGraduando = new PosGraduando(pessoa, projeto, tipo, dataInicio, duracao, horasSemanais, valorHora);
		return posGraduando;
	}

	public Participacao criaProfessor(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao,
			int horasSemanais, double valorHora, boolean coordenador) {
		Participacao prof = new Professor(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora, coordenador);
		return prof;
	}

	public Participacao criaProfissional(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao,
			int horasSemanais, double valorHora, String tipoProfissional) throws Exception {
		Participacao profissional;
		if (tipoProfissional.equalsIgnoreCase("DESENVOLVEDOR")) {
			profissional = new Profissional(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora,
					TipoProfissional.DESENVOLVEDOR);
			return profissional;
		} else if (tipoProfissional.equalsIgnoreCase("GERENTE")) {
			profissional = new Profissional(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora,
					TipoProfissional.GERENTE);
			return profissional;
		} else if (tipoProfissional.equalsIgnoreCase("PESQUISADOR")) {
			profissional = new Profissional(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora,
					TipoProfissional.PESQUISADOR);
			return profissional;
		}
		throw new Exception("Erro na associacao de pessoa a projeto: Tipo de profissional invalido");
	}

}
