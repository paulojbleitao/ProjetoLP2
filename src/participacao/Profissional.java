package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
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
	public void calculaValorBolsa() throws Exception {
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
		pessoa.addValorBolsa(bolsa);

	}

}
