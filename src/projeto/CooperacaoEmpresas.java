package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;

public class CooperacaoEmpresas extends PED {

	private double despesasEventuais;
	private double capital;

	public CooperacaoEmpresas(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo,
			HashSet<Producao> colecaoProd, double despesasConstantes, double despesasEventuais, double capital)
			throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo, colecaoProd, despesasConstantes);
		this.despesasEventuais = despesasEventuais;
		this.capital = capital;
		//tratamento dos valores
	}

	public double getDespesasEventuais() {
		return despesasEventuais;
	}

	public void setDespesasEventuais(double despesasEventuais) {
		this.despesasEventuais = despesasEventuais;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

}
