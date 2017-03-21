package gerenciador;

import factory.FactoryParticipacao;
import participacao.Participacao;
import pessoa.Pessoa;
import projeto.CooperacaoEmpresas;
import projeto.Extensao;
import projeto.Monitoria;
import projeto.PED;
import projeto.PET;
import projeto.ProgramaInst;
import projeto.Projeto;
import projeto.TipoProgramaInst;

public class GerenciadorParticipacao {

	private FactoryParticipacao factoryParticipacao;

	public GerenciadorParticipacao() {
		factoryParticipacao = new FactoryParticipacao();
	}

	public void associaGraduando(Pessoa pessoa, Projeto projeto, double valorHora, int qntHoras) throws Exception {
		if (projeto instanceof ProgramaInst && projeto.contemGraduando()) {
			throw new Exception(
					"Erro na associacao de pessoa a projeto: Projetos P&D nao podem ter mais de um graduando");
		} else if (projeto.contem(pessoa)) {
			throw new Exception("Erro na associacao de pessoa a projeto: Aluno ja esta cadastrado nesse projeto");
		}
		Participacao participacao = factoryParticipacao.criaGraduando(pessoa, projeto, projeto.getDataInicio(),
				projeto.getDuracao(), qntHoras, valorHora);
		if (!(projeto instanceof ProgramaInst && ((ProgramaInst) projeto).getTipoPI() == TipoProgramaInst.PIVIC)) {
			participacao.calculaValorBolsa();
		}
		pessoa.addParticipacao(participacao);
		projeto.addParticipacao(participacao);
	}

	public void associaPosGraduando(Pessoa pessoa, Projeto projeto, String nivel, double valorHora, int qntHoras)
			throws Exception {
		if (!(projeto instanceof Extensao) && !(projeto instanceof PED))
			throw new Exception("Erro na associacao de pessoa a projeto: Tipo de projeto invalido para pos graduando");
		Participacao participacao = factoryParticipacao.criaPosGraduando(pessoa, projeto, nivel,
				projeto.getDataInicio(), projeto.getDuracao(), qntHoras, valorHora);
		if (!(projeto instanceof ProgramaInst && ((ProgramaInst) projeto).getTipoPI() == TipoProgramaInst.PIVIC)) {
			participacao.calculaValorBolsa();
		}
		pessoa.addParticipacao(participacao);
		projeto.addParticipacao(participacao);
	}

	public void associaProfessor(Pessoa pessoa, Projeto projeto, boolean coordenador, double valorHora, int qntHoras)
			throws Exception {
		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}
		if (!(projeto instanceof Monitoria) && !(projeto instanceof ProgramaInst)) {
			if (valorHora <= 0) {
				throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
			}
		}
		if (projeto instanceof Monitoria) {
			if (valorHora != 0) {
				throw new Exception(
						"Erro na associacao de pessoa a projeto: Valor da hora de um professor da monitoria deve ser zero");
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
		if (projeto instanceof Extensao) {
			if (projeto.contemCoordenador()) {
				throw new Exception(
						"Erro na associacao de pessoa a projeto: Extensao nao pode ter mais de um coordenador");
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
		if (!(projeto instanceof ProgramaInst && ((ProgramaInst) projeto).getTipoPI() == TipoProgramaInst.PIVIC)) {
			participacao.calculaValorBolsa();
		}
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
		Participacao participacao = factoryParticipacao.criaProfissional(pessoa, projeto, projeto.getDataInicio(),
				projeto.getDuracao(), qntHoras, valorHora, cargo);
		participacao.calculaValorBolsa();
		pessoa.addParticipacao(participacao);
		projeto.addParticipacao(participacao);
	}

	public void removeParticipacao(Pessoa pessoa, Projeto projeto) {
		pessoa.removeParticipacao(projeto.getCodigo());
		projeto.removeParticipacao(pessoa.getCpf());
	}

}
