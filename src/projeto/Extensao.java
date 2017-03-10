package projeto;

import java.time.LocalDate;

public class Extensao extends Projeto {

	private int impacto;
	private double despesasEventuais;
	private double despesasConstantes;

	public Extensao(String nome, String objetivo, int impacto, LocalDate dataInicio, int duracao, String codigo)
			throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo);
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Erro no cadastro de projeto: Impacto invalida");
		}
		this.impacto = impacto;
		this.despesasEventuais = 0;
		this.despesasConstantes = 0;
	}

	public int getImpacto() {
		return impacto;
	}

	public void setImpacto(int impacto) {
		this.impacto = impacto;
	}

	public double getDespesasEventuais() {
		return despesasEventuais;
	}

	public void setDespesasEventuais(double despesasEventuais) {
		this.despesasEventuais = despesasEventuais;
	}

	public double getDespesasConstantes() {
		return despesasConstantes;
	}

	public void setDespesasConstantes(double despesasConstantes) {
		this.despesasConstantes = despesasConstantes;
	}

}
