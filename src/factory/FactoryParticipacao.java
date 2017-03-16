package factory;

import java.time.*;

import participacao.Graduando;
import participacao.Participacao;
import participacao.Professor;
import participacao.Profissional;
import participacao.TipoGraduando;
import participacao.TipoProfissional;
import pessoa.Pessoa;
import projeto.Projeto;

public class FactoryParticipacao {

	public Participacao criaGraduando(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao,
			int horasSemanais, double valorHora, String tipoGraduando) throws Exception {
		Participacao graduando;
		if (tipoGraduando.equalsIgnoreCase("GRADUANDO")) {
			graduando = new Graduando(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora,
					TipoGraduando.GRADUANDO);
			return graduando;
		} else if (tipoGraduando.equalsIgnoreCase("POSGRADUANDO_MESTRADO")) {
			graduando = new Graduando(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora,
					TipoGraduando.POSGRADUANDO_MESTRADO);
			return graduando;
		} else if (tipoGraduando.equalsIgnoreCase("POSGRADUANDO_DOUTORADO")) {
			graduando = new Graduando(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora,
					TipoGraduando.POSGRADUANDO_DOUTORADO);
			return graduando;
		}

		throw new Exception("Erro na associacao de pessoa a projeto: Tipo de graduando invalido");

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
