package gerenciador;

import java.time.*;
import java.util.HashSet;

import javafx.util.converter.LocalDateStringConverter;
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

	private boolean validaData(String dataInicio) throws Exception {
		if (dataInicio.trim().equals("") || dataInicio == null) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
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
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		Projeto monitoria = new Monitoria(nome, disciplina, objetivo, rendimento, data, duracao, codigoStr);
		projetos.add(monitoria);
		return codigoStr;
	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica, int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		FactoryPED obterProducao = new FactoryPED();
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		HashSet<Producao> colecaoProd = obterProducao.getColecaoProd(prodTecnica, prodAcademica, patentes);
		Projeto pet = new PET(nome, objetivo, data, duracao, codigoStr, impacto, rendimento, colecaoProd);
		projetos.add(pet);
		return codigoStr;
	}

	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao) throws Exception {
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		Projeto extensao = new Extensao(nome, objetivo, impacto, data, duracao, codigoStr);
		projetos.add(extensao);
		return codigoStr;
	}

	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivo, String dataInicio, int duracao) throws Exception {
		FactoryPED factoryPED = new FactoryPED(); 		
		int codigoInt = ThreadLocalRandom.current().nextInt(1, 101);
		String codigoStr = "codigo" + codigoInt;
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		Projeto ped = factoryPED.criaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, data, duracao, codigoStr);
		projetos.add(ped);
		return codigoStr;
	}

}
