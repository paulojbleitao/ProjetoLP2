package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;
import producao.Produtividade;

public class CooperacaoEmpresas extends PED implements Colaborativo {

	private double despesasEventuais;
	private double capital;

	public CooperacaoEmpresas(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo,
			HashSet<Producao> colecaoProd) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
		this.despesasEventuais = 0;
		this.capital = 0;
		// tratamento dos valores
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

	@Override
	public double calculaColaboracao() {
		if (despesasEventuais + capital <= 10000) {
			return 0;
		}
		double porcentagem = 0.1;
		for (Producao p : colecaoProd) {
			if (p.getProdutividade() == Produtividade.PATENTES) {
				porcentagem += 0.03;
				break;
			}
		}
		for (Producao p : colecaoProd) {
			if (p.getProdutividade() == Produtividade.PRODUCAO_TECNICA) {
				porcentagem += 0.003;
			}
		}
		for (Producao p : colecaoProd) {
			if (p.getProdutividade() == Produtividade.PRODUCAO_ACADEMICA) {
				porcentagem -= 0.002;
			}
		}
		return (despesasEventuais + capital) * porcentagem;

	}

}
