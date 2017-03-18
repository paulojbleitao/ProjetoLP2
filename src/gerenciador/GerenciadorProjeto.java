package gerenciador;

import java.time.*;
import java.util.HashSet;

import javafx.util.converter.LocalDateStringConverter;
import producao.FactoryProducao;
import producao.Producao;
import projeto.Extensao;
import projeto.Monitoria;
import projeto.PED;
import projeto.PET;
import projeto.Projeto;
import java.util.concurrent.ThreadLocalRandom;

import factory.FactoryPED;

public class GerenciadorProjeto {

	HashSet<Projeto> projetos;

	public GerenciadorProjeto() {
		projetos = new HashSet<>();
	}
	
	public Projeto buscaProjeto(String codigo) throws Exception {
		for (Projeto projeto : projetos) {
			if (projeto.getCodigo().equals(codigo)) {
				return projeto;
			}
		}
		throw new Exception("Erro na consulta de projeto: Projeto nao encontrado");
	}

	private void validaProjeto(String nome, String objetivo, int duracao) throws Exception {
		if (nome == null || nome.trim().equals("")) {
			throw new Exception("Erro no cadastro de projeto: Nome nulo ou vazio");
		}
		if (objetivo == null || objetivo.trim().equals("")) {
			throw new Exception("Erro no cadastro de projeto: Objetivo nulo ou vazio");
		}
		if (duracao <= 0) {
			throw new Exception("Erro no cadastro de projeto: Duracao invalida");
		}
	}

	private boolean validaData(String dataInicio) {
		if (dataInicio.trim().equals("") || dataInicio == null) {
			return false;
		}
		if (dataInicio.length() != 10) {
			return false;
		}
		if (dataInicio.charAt(2) != '/' || dataInicio.charAt(5) != '/') {
			return false;
		}
		try {
			this.converteData(dataInicio);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	private LocalDate converteData(String dataInicio) {
		LocalDateStringConverter conversor = new LocalDateStringConverter();
		LocalDate data = conversor.fromString(dataInicio);
		return data;
	}

	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		validaProjeto(nome, objetivo, duracao);
		if (disciplina == null || disciplina.trim().equals("")) {
			throw new Exception("Erro no cadastro da disciplina: Disciplina nulo ou vazia");
		}
		if (rendimento < 0 || rendimento > 100) {
			throw new Exception("Erro no cadastro de projeto: Rendimento invalido");
		}
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		LocalDate data = this.converteData(dataInicio);
		Projeto monitoria = new Monitoria(nome, disciplina, objetivo, rendimento, data, duracao, codigoStr);
		projetos.add(monitoria);
		return codigoStr;
	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		validaProjeto(nome, objetivo, duracao);
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Erro no cadastro de projeto: Impacto invalido");
		}
		if (rendimento < 0 || rendimento > 100) {
			throw new Exception("Erro no cadastro de projeto: Rendimento invalido");
		}
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
		if (colecaoProd == null || colecaoProd.size() == 0) {
			throw new Exception("Erro no cadastro de projeto: Colecao de produtividade nulo ou vazio");
		}
		Projeto pet = new PET(nome, objetivo, data, duracao, codigoStr, impacto, rendimento, colecaoProd);
		projetos.add(pet);
		return codigoStr;
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		validaProjeto(nome, objetivo, duracao);
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Erro no cadastro de projeto: Impacto invalido");
		}
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		LocalDate data = this.converteData(dataInicio);
		Projeto extensao = new Extensao(nome, objetivo, impacto, data, duracao, codigoStr);
		projetos.add(extensao);
		return codigoStr;
	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		validaProjeto(nome, objetivo, duracao);
		FactoryPED factoryPED = new FactoryPED();
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
		Projeto ped = factoryPED.criaPED(nome, categoria, colecaoProd, objetivo, data, duracao, codigoStr);
		projetos.add(ped);
		return codigoStr;

	}
	
	public String getCodigoProjeto(String nome) {
		for (Projeto p: projetos) {
			if (p.getNome().equalsIgnoreCase(nome))
				return p.getCodigo();
		}
		return null;
	}
	
	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		Projeto p = buscaProjeto(codigo);
		
		switch(atributo.toUpperCase()) {
			case "NOME":
				return p.getNome();
			case "OBJETIVO":
				return p.getObjetivo();
			case "DATA DE INICIO":
				LocalDateStringConverter conversor = new LocalDateStringConverter();
				return conversor.toString(p.getDataInicio());
			case "DURACAO":
				return "" + p.getDuracao();
			case "PRODUCAO TECNICA":
				if (p instanceof PET) {
					PET p2 = (PET) p;
					return "" + p2.getProducaoTecnica();
				} else if (p instanceof PED) {
					PED p2 = (PED) p;
					return "" + p2.getProducaoTecnica();
				}
				break;
			case "PRODUCAO ACADEMICA":
				if (p instanceof PET) {
					PET p2 = (PET) p;
					return "" + p2.getProducaoAcademica();
				} else if (p instanceof PED) {
					PED p2 = (PED) p;
					return "" + p2.getProducaoAcademica();
				} else if (p instanceof Monitoria) {
					throw new Exception("Erro na consulta de projeto: Monitoria nao possui Producao academica");
				}
				break;
			case "PARTICIPACOES":
				return p.participacoesDeProjeto();
			default:
				break;
		}
		throw new Exception("Erro na consulta de projeto: Atributo nulo ou invalido");
	}
	
	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		Projeto p = buscaProjeto(codigo);
		if (atributo.equalsIgnoreCase("nome")) {
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de projeto: Nome nulo ou vazio");
			p.setNome(valor);
		} else if (atributo.equalsIgnoreCase("objetivo")) {
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de projeto: Objetivo nulo ou vazio");
			p.setObjetivo(valor);
		} else if (atributo.equalsIgnoreCase("duracao")) {
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de projeto: Duracao nula ou vazia");
			p.setDuracao(Integer.parseInt(valor));
		} else if (atributo.equalsIgnoreCase("data de inicio")) {
			if (validaData(valor) == false)
				throw new Exception("Erro na atualizacao de projeto: Formato de data invalido");
			p.setDataInicio(converteData(valor));
		}
	}
	
	public void removeProjeto(String codigo) throws Exception {
		Projeto p = buscaProjeto(codigo);
		projetos.remove(p);
	}
	
	private HashSet<Producao> getColecaoProd(int prodTecnica, int prodAcademica, int patentes) throws Exception {
		if (prodTecnica < 0) {
			throw new Exception("Erro no cadastro de projeto: Numero de producoes tecnicas invalido");
		}
		if (prodAcademica < 0) {
			throw new Exception("Erro no cadastro de projeto: Numero de producoes academicas invalido");
		}
		if (patentes < 0) {
			throw new Exception("Erro no cadastro de projeto: Numero de patentes invalido");
		}
		HashSet<Producao> colecaoProd = new HashSet<>();
		FactoryProducao factoryProd = new FactoryProducao();
		if (prodTecnica > 0) {
			Producao prodTec = factoryProd.criaProducao("producaoTecnica", prodTecnica);
			colecaoProd.add(prodTec);
		}
		if (prodAcademica > 0) {
			Producao prodAca = factoryProd.criaProducao("producaoAcademica", prodAcademica);
			colecaoProd.add(prodAca);
		}
		if (patentes > 0) {
			Producao pat = factoryProd.criaProducao("patentes", patentes);
			colecaoProd.add(pat);
		}
		return colecaoProd;
	}

}
