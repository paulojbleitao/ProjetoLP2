package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;

public class ProgramaInst extends PED {

	private TipoProgramaInst tipoPI;

	public ProgramaInst(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo,
			HashSet<Producao> colecaoProd, TipoProgramaInst tipoPI) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
		this.tipoPI = tipoPI;
		
	}

	public TipoProgramaInst getTipoPI() {
		return tipoPI;
	}

}
