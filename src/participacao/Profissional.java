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

}
