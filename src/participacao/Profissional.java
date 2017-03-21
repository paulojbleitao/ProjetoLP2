package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.PED;
import projeto.Projeto;

public class Profissional extends Participacao {

	private TipoProfissional tipoProfissional;

	public Profissional(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao, int horasSemanais,
			double valorHora, TipoProfissional tipoProfissional) {
		super(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);
		this.tipoProfissional = tipoProfissional;
	}

	public TipoProfissional getTipoProfissional() {
		return tipoProfissional;
	}
	
	@Override
	public double calculaPontos() {
		super.calculaPontos();
		if (this.getProjeto() instanceof PED) {
			int pontos = 0;
			if (this.tipoProfissional == TipoProfissional.DESENVOLVEDOR)
				pontos += 5 * (this.getDuracao() / 12);
			else if (this.tipoProfissional  == TipoProfissional.PESQUISADOR)
				pontos += 6 * (this.getDuracao() / 12);
			else if (this.tipoProfissional  == TipoProfissional.GERENTE)
				pontos += 9 * (this.getDuracao() / 12);
			return pontos;
		}
		return 0;
	}

	@Override
	public double calculaValorBolsa() {
		double bolsa = horasSemanais * valorHora;
		if (tipoProfissional == TipoProfissional.PESQUISADOR) {
			bolsa += 100;
		}
		if (tipoProfissional == TipoProfissional.GERENTE) {
			int participantes = projeto.getParticipacoes().size();
			if (participantes > 5) {
				bolsa += 100;
			} else {
				bolsa += participantes * 20;
			}
		}
		if (bolsa < 350) {
			bolsa = 350;
		}
		return bolsa;

	}

}
