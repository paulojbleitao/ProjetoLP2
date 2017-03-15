package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;

public class ProgramasInst extends PED {

	private String tipo;

	public ProgramasInst(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo,
			HashSet<Producao> colecaoProd, String tipo) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
		this.tipo = tipo;
		// tratamento do tipo
	}

	public String getTipo() {
		return tipo;
	}

}
