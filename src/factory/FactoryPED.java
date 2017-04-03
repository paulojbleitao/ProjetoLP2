package factory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;
import projeto.CooperacaoEmpresas;
import projeto.PED;
import projeto.ProgramaInst;
import projeto.TipoProgramaInst;

public class FactoryPED implements Serializable {

	private static final long serialVersionUID = 1L;

	public PED criaPED(String nome, String categoria, HashSet<Producao> colecaoProd, String objetivo,
			LocalDate dataInicio, int duracao, int codigo) throws Exception {
		String categoriaCL = categoria.toUpperCase();
		if (categoriaCL.equals("PIBIC")) {
			PED progInst = new ProgramaInst(nome, objetivo, dataInicio, duracao, codigo, colecaoProd,
					TipoProgramaInst.PIBIC);
			return progInst;
		} else if (categoriaCL.equals("PIBITI")) {
			PED progInst = new ProgramaInst(nome, objetivo, dataInicio, duracao, codigo, colecaoProd,
					TipoProgramaInst.PIBITI);
			return progInst;
		} else if (categoriaCL.equals("PIVIC")) {
			PED progInst = new ProgramaInst(nome, objetivo, dataInicio, duracao, codigo, colecaoProd,
					TipoProgramaInst.PIVIC);
			return progInst;
		} else if (categoriaCL.equals("COOP")) {
			PED coop = new CooperacaoEmpresas(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
			return coop;
		}
		throw new Exception("Erro no cadastro de projeto: Categoria invalida");
	}

}
