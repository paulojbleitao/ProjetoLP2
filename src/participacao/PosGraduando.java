package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;

public class PosGraduando extends Participacao {

	private TipoPosGraduando tipoPosGraduando;

	public PosGraduando(Pessoa pessoa, Projeto projeto, TipoPosGraduando tipo, LocalDate dataInicio, int duracao,
			int horasSemanais, double valorHora) {
		super(pessoa, projeto, dataInicio, duracao, horasSemanais, valorHora);
		this.tipoPosGraduando = tipo;
	}

	public TipoPosGraduando getTipoGraduando() {
		return tipoPosGraduando;
	}

	public void setTipoGraduando(TipoPosGraduando tipoGraduando) {
		this.tipoPosGraduando = tipoGraduando;
	}

	@Override
	public void calculaValorBolsa() throws Exception {
		double bolsa = horasSemanais * valorHora;
		if (tipoPosGraduando == TipoPosGraduando.POSGRADUANDO_DOUTORADO) {
			bolsa += bolsa / 3;
		}
		if (bolsa < 350) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da bolsa inferior a 350");
		}
		pessoa.addValorBolsa(bolsa);

	}

}
