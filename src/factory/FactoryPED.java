package factory;

import java.time.LocalDate;
import java.util.HashSet;

import producao.Producao;
import projeto.CooperacaoEmpresas;
import projeto.PED;
import projeto.ProgramasInst;

public class FactoryPED {

	public PED criaPED(String nome, String categoria, HashSet<Producao> colecaoProd, String objetivo,
			LocalDate dataInicio, int duracao, String codigo) throws Exception {
		String categoriaCapsLock = categoria.toUpperCase();
		if (categoria.equals("PIBIC") || categoria.equals("PIBIT") || categoria.equals("PIVIC")) {
			PED progInst = new ProgramasInst(nome, objetivo, dataInicio, duracao, codigo, colecaoProd,
					categoriaCapsLock);
			return progInst;
		} else if (categoria.equals("COOP")) {
			PED coop = new CooperacaoEmpresas(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
			return coop;
		}
		throw new Exception("Erro no cadastro de projeto: Tipo invalido");
	}

}
