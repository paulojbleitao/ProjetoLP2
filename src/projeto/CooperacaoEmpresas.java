package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;

public class CooperacaoEmpresas extends PED {

	private double despesasEventuais;
	private double capital;

	public CooperacaoEmpresas(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo,
			HashSet<Producao> colecaoProd)
			throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
		this.despesasEventuais = 0;
		this.capital = 0;
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
