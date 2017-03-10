package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;

public class PED extends Projeto {

	protected HashSet<Producao> colecaoProd;
	protected double despesasConstantes;

	public PED(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo,
			HashSet<Producao> colecaoProd) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.colecaoProd = colecaoProd;
		this.despesasConstantes = 0;
		// falta o tratamento de colecaoProd ser null, e as despesas contantes
	}

	public double getDespesasConstantes() {
		return despesasConstantes;
	}

	public void setDespesasConstantes(double despesasConstantes) {
		this.despesasConstantes = despesasConstantes;
	}

	public HashSet<Producao> getColecaoProd() {
		return colecaoProd;
	}

}


