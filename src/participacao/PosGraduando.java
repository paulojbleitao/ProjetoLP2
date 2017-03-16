package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;

public class PosGraduando extends Participacao {

	private TipoPosGraduando tipoGraduando;

	public PosGraduando(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao, int horasSemanais,
			double valorHora) {
		super(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);

	}

	public TipoPosGraduando getTipoGraduando() {
		return tipoGraduando;
	}

	public void setTipoGraduando(TipoPosGraduando tipoGraduando) {
		this.tipoGraduando = tipoGraduando;
	}

}
