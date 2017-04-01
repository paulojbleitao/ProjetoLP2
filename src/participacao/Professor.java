package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;
import projeto.Monitoria;

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

	@Override
	public double calculaValorBolsa() {
		if (this.getProjeto().getClass() == Monitoria.class)
			return 0;
		double bolsa = horasSemanais * valorHora;
		if (coordenador) {
			bolsa += bolsa * 0.4;
		}
		return Math.max(bolsa, 350);
	}

}
