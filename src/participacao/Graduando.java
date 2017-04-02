package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;

public class Graduando extends Participacao {
	
	private static final long serialVersionUID = 1L;

	public Graduando(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao, int horasSemanais,
			double valorHora) {
		super(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);

	}

	@Override
	public double calculaValorBolsa() {
		double bolsa = horasSemanais * valorHora;
		return Math.max(bolsa, 350);
	}

}