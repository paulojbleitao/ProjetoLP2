package projeto;

import java.time.LocalDate;

public class Extensao extends Projeto implements Colaborativo {

	private static final long serialVersionUID = 1L;
	private int impacto;
	private double despesasEventuais;
	private double despesasConstantes;

	public Extensao(String nome, String objetivo, int impacto, LocalDate dataInicio, int duracao, int codigo)
			throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo);
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

	@Override
	public double calculaColaboracao() {
		if (despesasEventuais <= 10000) {
			return 0;
		}
		double porcentagem = 0.1;
		porcentagem -= impacto * 0.005;
		return (despesasEventuais + despesasConstantes) * porcentagem;
	}

	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) throws Exception {
		if (montanteCapital > 0)
			throw new Exception("Erro na atualizacao de projeto: projeto do tipo Extensao nao permite despesas de capital");
		this.despesasConstantes = montanteBolsas;
		this.despesasEventuais = montanteCusteio;
	}

}
