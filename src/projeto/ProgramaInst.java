package projeto;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;

public class ProgramaInst extends PED {

	private static final long serialVersionUID = 1L;
	private TipoProgramaInst tipoPI;

	public ProgramaInst(String nome, String objetivo, LocalDate dataInicio, int duracao, int codigo,
			HashSet<Producao> colecaoProd, TipoProgramaInst tipoPI) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
		this.tipoPI = tipoPI;
		
	}

	public TipoProgramaInst getTipoPI() {
		return tipoPI;
	}

	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) throws Exception {
		if (montanteBolsas <= 0 && this.tipoPI != TipoProgramaInst.PIVIC)
			throw new Exception("Erro na atualizacao de projeto: projeto do tipo P&D - PIBIC ou PIBIT deve permitir despesas de bolsas");
		else if ((montanteCusteio > 0 || montanteCapital > 0) && this.tipoPI != TipoProgramaInst.PIVIC)
			throw new Exception("Erro na atualizacao de projeto: projeto do tipo P&D - PIBIC ou PIBIT nao permite despesas de custeio ou capital");
		this.despesasConstantes = montanteBolsas;
	}

	@Override
	public double calculaColaboracao() {
		return 0;
	}
	
}
