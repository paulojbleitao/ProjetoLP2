package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;
import producao.Produtividade;

public class CooperacaoEmpresas extends PED implements Colaborativo {

	private static final long serialVersionUID = 1L;
	private double despesasEventuais;
	private double capital;

	public CooperacaoEmpresas(String nome, String objetivo, LocalDate dataInicio, int duracao, int codigo,
			HashSet<Producao> colecaoProd) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
		this.despesasEventuais = 0;
		this.capital = 0;
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
				porcentagem += 0.003 * p.getQuant();
			}
		}
		for (Producao p : colecaoProd) {
			if (p.getProdutividade() == Produtividade.PRODUCAO_ACADEMICA) {
				porcentagem -= 0.002 * p.getQuant();
			}
		}
		porcentagem += 0.01 * (int) (capital / 100000);
		return (despesasConstantes + despesasEventuais + capital) * porcentagem;
	}

	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) throws Exception {
		if (montanteBolsas <= 0 || montanteCusteio <= 0 || montanteCapital <= 0)
			throw new Exception("Erro na atualizacao de projeto: projeto do tipo Coop devem possuir todas as despesas");
		this.despesasConstantes = montanteBolsas;
		this.despesasEventuais = montanteCusteio;
		this.capital = montanteCapital;
	}

}
