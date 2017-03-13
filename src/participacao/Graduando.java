package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;

public class Graduando extends Participacao {

	private TipoGraduando tipoGraduando;

	public Graduando(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao, int horasSemanais,
			double valorHora, TipoGraduando tipoGraduando) {
		super(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);
		this.tipoGraduando = tipoGraduando;
	}

	public TipoGraduando getTipoGraduando() {
		return tipoGraduando;
	}

}
