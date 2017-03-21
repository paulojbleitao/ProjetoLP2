package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;

public class Graduando extends Participacao {

	public Graduando(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao, int horasSemanais,
			double valorHora) {
		super(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);

	}

	@Override
	public double calculaValorBolsa() {
		double bolsa = horasSemanais * valorHora;
		return bolsa;
	}

}