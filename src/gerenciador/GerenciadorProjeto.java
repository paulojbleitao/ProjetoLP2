package gerenciador;

import java.time.*;
import java.util.HashSet;

import javafx.util.converter.LocalDateStringConverter;
import producao.FactoryProducao;
import producao.Producao;
import projeto.Extensao;
import projeto.Monitoria;
import projeto.PET;
import projeto.Projeto;
import java.util.concurrent.ThreadLocalRandom;

import factory.FactoryPED;

public class GerenciadorProjeto {

	HashSet<Projeto> projetos;

	public Projeto buscaProjeto(String codigo) throws Exception {
		for (Projeto projeto : projetos) {
			if (projeto.getCodigo().equals(codigo)) {
				return projeto;
			}
		}
		throw new Exception("Erro na consulta de Projeto: Projeto nao encontrado");
	}

	public GerenciadorProjeto() {
		projetos = new HashSet<>();
	}

	public void iniciaSistema() {
	}

	public void fechaSistema() {
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
		if (dataInicio.charAt(3) != '/' || dataInicio.charAt(6) != '/') {
			return false;
		}
		LocalDate data = this.converteData(dataInicio);
		if (data.getDayOfMonth() > data.lengthOfMonth() || data.getDayOfMonth() < 1) {
			return false;
		}
		if (data.getMonthValue() > 12 || data.getMonthValue() < 1) {
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

	private HashSet<Producao> getColecaoProd(int prodTecnica, int prodAcademica, int patentes) throws Exception {
		if (prodTecnica < 0) {
			throw new Exception("Erro no cadastro de producao: Quantidade invalida");
		}
		if (prodAcademica < 0) {
			throw new Exception("Erro no cadastro de producao: Quantidade invalida");
		}
		if (patentes < 0) {
			throw new Exception("Erro no cadastro de producao: Quantidade invalida");
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
