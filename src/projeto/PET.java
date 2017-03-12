package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;

public class PET extends Projeto {
	
	private int impacto;
	private int rendimento;
	private HashSet<Producao> colecaoProd;
	private double despesasConstantes;
	private double despesasEventuais;
	
	public PET(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo,
			int impacto, int rendimento, HashSet<Producao> colecaoProd) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.impacto = impacto;
		this.rendimento = rendimento;
		this.colecaoProd = colecaoProd;
		this.despesasConstantes = 0;
		this.despesasEventuais = 0;
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Erro no cadastro de projeto: Rendimento invalido");
		}
		if (rendimento < 0 || rendimento > 100) {
			throw new Exception("Erro no cadastro de projeto: Rendimento invalido");
		}
		if (colecaoProd == null || colecaoProd.size() == 0) {
			throw new Exception("Erro no cadastro de projeto: Colecao de produtividade nulo ou vazio");
		}
			
	}

	public int getImpacto() {
		return impacto;
	}

	public void setImpacto(int impacto) {
		this.impacto = impacto;
	}

	public int getRendimento() {
		return rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

	public HashSet<Producao> getColecaoProd() {
		return colecaoProd;
	}

	public void setColecaoProd(HashSet<Producao> colecaoProd) {
		this.colecaoProd = colecaoProd;
	}

	public double getDespesasConstantes() {
		return despesasConstantes;
	}

	public void setDespesasConstantes(double despesasConstantes) {
		this.despesasConstantes = despesasConstantes;
	}

	public double getDespesasEventuais() {
		return despesasEventuais;
	}

	public void setDespesasEventuais(double despesasEventuais) {
		this.despesasEventuais = despesasEventuais;
	}
	
}
