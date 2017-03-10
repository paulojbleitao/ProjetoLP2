package factory;

import java.time.LocalDate;
import java.util.HashSet;

import producao.FactoryProducao;
import producao.Producao;
import projeto.CooperacaoEmpresas;
import projeto.PED;
import projeto.ProgramasInst;

public class FactoryPED {

	public PED criaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo,
			LocalDate dataInicio, int duracao, String codigo) throws Exception {
		if (categoria.equalsIgnoreCase("PIBIC")) {
			HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
			PED pibic = new ProgramasInst(nome, objetivo, dataInicio, duracao, codigo, colecaoProd, "PIBIC");
			return pibic;
		} else if (categoria.equalsIgnoreCase("PIBIT")) {
			HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
			PED pibiti = new ProgramasInst(nome, objetivo, dataInicio, duracao, codigo, colecaoProd, "PIBIT");
			return pibiti;

		} else if (categoria.equalsIgnoreCase("PIVIC")) {
			HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
			PED pivic = new ProgramasInst(nome, objetivo, dataInicio, duracao, codigo, colecaoProd, "PIVIC");
			return pivic;			
		} else if (categoria.equalsIgnoreCase("COOP")) {
			HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
			PED coop = new CooperacaoEmpresas(nome, objetivo, dataInicio, duracao, codigo, colecaoProd);
			return coop;
		}
		throw new Exception("Erro no cadastro de projeto: Tipo invalido");
	}

	public HashSet<Producao> getColecaoProd(int prodTecnica, int prodAcademica, int patentes) throws Exception {
		HashSet<Producao> colecaoProd = new HashSet<>();
		FactoryProducao factoryProd = new FactoryProducao();
		Producao prodTec = factoryProd.criaProducao("producaoTecnica", prodTecnica);
		Producao prodAca = factoryProd.criaProducao("producaoAcademica", prodAcademica);
		Producao pat = factoryProd.criaProducao("patentes", patentes);
		colecaoProd.add(prodTec);
		colecaoProd.add(prodAca);
		colecaoProd.add(pat);
		return colecaoProd;
	}

}
