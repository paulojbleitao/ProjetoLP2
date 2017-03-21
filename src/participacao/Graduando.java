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
	public void calculaValorBolsa() throws Exception {
		double bolsa = horasSemanais * valorHora;
		if (bolsa < 350) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da bolsa inferior a 350");
		}
		pessoa.addValorBolsa(bolsa);
	}

}
