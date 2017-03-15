package gerenciador;

import factory.FactoryParticipacao;
import participacao.Participacao;
import pessoa.Pessoa;
import projeto.CooperacaoEmpresas;
import projeto.Monitoria;
import projeto.PED;
import projeto.PET;
import projeto.Projeto;

public class GerenciadorParticipacao {

	private FactoryParticipacao factoryParticipacao;

	public void associaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) throws Exception {
		if (projeto instanceof PED) {
			if (projeto.contemGraduando()) {
				throw new Exception(
						"Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um graduando");
			}

		}
		Participacao participacao = factoryParticipacao.criaGraduando(pessoa, projeto, projeto.getDataInicio(),
				projeto.getDuracao(), qntHoras, valorHora, "GRADUANDO");
		pessoa.addParticipacao(participacao);
		projeto.addParticipacao(participacao);
	}

	public void associaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora, int qntHoras)
			throws Exception {
		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}
		if (!(projeto instanceof Monitoria)) {
			if (valorHora <= 0) {
				throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
			}
		}
		if (projeto instanceof Monitoria) {
			if (projeto.contemProfessor()) {
				throw new Exception(
						"Erro na associacao de pessoa a projeto: Monitoria nao pode ter mais de um professor");
			}
			if (valorHora != 0) {
				throw new Exception(
						"Erro na associacao de pessoa a projeto: Valor da hora de um professor da monitoria deve ser zero");
			}
		}
		if (projeto instanceof PET) {
			if (projeto.contemProfessor()) {
				throw new Exception("Erro na associacao de pessoa a projeto: PET nao pode ter mais de um professor");
			}
			if (!coordenador) {
				throw new Exception("Erro na associacao de pessoa a projeto: PET deve ter um coordenador");
			}
		}
		if (projeto instanceof CooperacaoEmpresas) {
			if (projeto.contemCoordenador()) {
				throw new Exception("Erro na associacao de pessoa a projeto: COOP nao pode ter mais de um coordenador");
			}
		}
		if (projeto instanceof PED) {
			if (coordenador && projeto.contemCoordenador()) {
				throw new Exception(
						"Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um coordenador");
			}
			if (projeto.contemProfessor()) {
				throw new Exception(
						"Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um professor");
			}

		}
		Participacao participacao = factoryParticipacao.criaProfessor(pessoa, projeto, projeto.getDataInicio(),
				projeto.getDuracao(), qntHoras, valorHora, coordenador);
		pessoa.addParticipacao(participacao);
		projeto.addParticipacao(participacao);
	}

	public void associaProfissional(Pessoa pessoa, Projeto projeto, String cargo, double valorHora, int qntHoras)
			throws Exception {
		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}
		if (valorHora <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		if (!(projeto instanceof CooperacaoEmpresas)) {
			throw new Exception("Erro na associacao de pessoa a projeto: Este projeto nao pode ter profissional");
		}

	}

}
