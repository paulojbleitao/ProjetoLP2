package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;

public class Professor extends Participacao {

	private boolean coordenador;

	public Professor(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao, int horasSemanais,
			double valorHora, boolean coordenador) {
		super(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);
		this.coordenador = coordenador;
	}

	public boolean isCoordenador() {
		return coordenador;
	}

}
