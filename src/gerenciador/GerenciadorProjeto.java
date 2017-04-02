package gerenciador;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.util.converter.LocalDateStringConverter;
import producao.FactoryProducao;
import producao.Producao;
import projeto.Extensao;
import projeto.Monitoria;
import projeto.PED;
import projeto.PET;
import projeto.Projeto;

import factory.FactoryPED;

public class GerenciadorProjeto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashSet<Projeto> projetos;
	private int contador;
	private double colaboracao;
	private double valorGasto;

	public GerenciadorProjeto() {
		projetos = new HashSet<>();
		contador = 0;
		colaboracao = 0.0;
		valorGasto = 0.0;
	}

	public Projeto buscaProjeto(String codigo) throws Exception {
		if (codigo == null || codigo.trim().equals(""))
			throw new Exception("Erro na consulta de projeto: codigo nulo ou vazio");
		for (Projeto projeto : projetos) {
			if (projeto.getCodigo() == Integer.parseInt(codigo)) {
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
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private LocalDate converteData(String dataInicio) {
		LocalDateStringConverter conversor = new LocalDateStringConverter();
		LocalDate data = conversor.fromString(dataInicio);
		return data;
	}

	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
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
		contador++;
		LocalDate data = this.converteData(dataInicio);
		Projeto monitoria = new Monitoria(nome, disciplina, objetivo, rendimento, data, duracao, contador);
		projetos.add(monitoria);
		return contador;
	}

	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		validaProjeto(nome, objetivo, duracao);
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Erro no cadastro de projeto: Impacto invalido");
		}
		if (rendimento < 0 || rendimento > 100) {
			throw new Exception("Erro no cadastro de projeto: Rendimento invalido");
		}
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
		if (colecaoProd == null || colecaoProd.size() == 0) {
			throw new Exception("Erro no cadastro de projeto: Colecao de produtividade nulo ou vazio");
		}
		contador++;
		Projeto pet = new PET(nome, objetivo, data, duracao, contador, impacto, rendimento, colecaoProd);
		projetos.add(pet);
		return contador;
	}

	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		validaProjeto(nome, objetivo, duracao);
		if (impacto < 1 || impacto > 6) {
			throw new Exception("Erro no cadastro de projeto: Impacto invalido");
		}
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		contador++;
		LocalDate data = this.converteData(dataInicio);
		Projeto extensao = new Extensao(nome, objetivo, impacto, data, duracao, contador);
		projetos.add(extensao);
		return contador;
	}

	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		validaProjeto(nome, objetivo, duracao);
		FactoryPED factoryPED = new FactoryPED();
		if (!(validaData(dataInicio))) {
			throw new Exception("Erro no cadastro de projeto: Data nula ou vazia");
		}
		LocalDate data = this.converteData(dataInicio);
		HashSet<Producao> colecaoProd = getColecaoProd(prodTecnica, prodAcademica, patentes);
		contador++;
		Projeto ped = factoryPED.criaPED(nome, categoria, colecaoProd, objetivo, data, duracao, contador);
		projetos.add(ped);
		return contador;
	}

	public int getCodigoProjeto(String nome) {
		for (Projeto p : projetos) {
			if (p.getNome().equalsIgnoreCase(nome))
				return p.getCodigo();
		}
		return 0;
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		Projeto p = buscaProjeto(codigo);

		switch (atributo.toUpperCase()) {
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
		switch(atributo.toUpperCase()) {
		case "NOME":
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de projeto: Nome nulo ou vazio");
			p.setNome(valor);
			break;
		case "OBJETIVO":
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de projeto: Objetivo nulo ou vazio");
			p.setObjetivo(valor);
			break;
		case "DURACAO":
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de projeto: Duracao nula ou vazia");
			p.setDuracao(Integer.parseInt(valor));
			break;
		case "DATA DE INICIO":
			if (validaData(valor) == false)
				throw new Exception("Erro na atualizacao de projeto: Formato de data invalido");
			p.setDataInicio(converteData(valor));
			break;
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

	public void atualizaDespesasProjeto(String codigo, double montanteBolsas, double montanteCusteio, double montanteCapital) throws Exception {
		if (codigo == null || codigo.trim().equals(""))
			throw new Exception("Erro na atualizacao de projeto: codigo nulo ou vazio");
		else if (montanteBolsas < 0 || montanteCusteio < 0 || montanteCapital < 0)
			throw new Exception("Erro na atualizacao de projeto: valor negativo");
		Projeto p = this.buscaProjeto(codigo);
		p.atualizaDespesas(montanteBolsas, montanteCusteio, montanteCapital);
	}
	
	public double calculaColaboracao(String codigo) throws Exception {
		Projeto p = this.buscaProjeto(codigo);
		double colaboracao = p.calculaColaboracao();
		this.colaboracao += colaboracao;
		return colaboracao;
	}
	

	public void diminuiReceita(double valor) throws Exception {
		if (valor < 0)
			throw new Exception("Erro na atualizacao da receita da unidade: valor negativo");
		else if (valor > (colaboracao - valorGasto))
			throw new Exception("Erro na atualizacao da receita da unidade: a unidade nao possui fundos suficientes");
		valorGasto += valor;
	}
	
	public double getColaboracao() {
		return colaboracao;
	}

	public double getTotalEmCaixa() {
		return colaboracao - valorGasto;
	}
	
	public String historicoColaboracoes() {
		final String NL = System.lineSeparator();
		String str = "Historico das colaboracoes: " + NL;
		ArrayList<Projeto> al = new ArrayList<>(projetos);
		al.sort(null);
		
		for (Projeto p: al) {
			str += "==> Nome: " + p.getNome() + ", Data de inicio: " + p.getDataInicio().toString() + ", Valor colaborado: R$" + p.calculaColaboracao() + NL;
		}
		str += "Total acumulado com colaboracoes: R$" + this.colaboracao + NL +
				"Total gasto: R$" + this.valorGasto + NL +
				"Total em caixa: R$" + this.getTotalEmCaixa() + NL;
	
		return str;
	}
	
	@Override
	public String toString() {
		final String NL = System.lineSeparator();
		String str = "Cadastro de projetos: " + projetos.size() + " projeto(s) registrado(s)" + NL;
		int finalizados = 0;
		double graduandos = 0;
		double posgraduandos = 0;
		double profissionais = 0;
		ArrayList<Projeto> al = new ArrayList<>(projetos);
		al.sort(null);
		
		for (int i = 0; i < al.size(); i++) {
			Projeto p = al.get(i);
			if (p.isFinalizado())
				finalizados++;
			graduandos += p.getQntdGraduandos() - p.getQntdPosGraduandos();
			posgraduandos += p.getQntdPosGraduandos();
			profissionais = p.getQntdProfissionais();
			str += "==> Projeto " + (i+1) + ":" + NL +
					p.toString() + NL;
		}
		double total = graduandos + posgraduandos + profissionais;
		str += "Total de projetos concluidos: " + finalizados + NL +
				"% Participacao da graduacao: " + (graduandos / total) * 100 + "%" + NL +
				"% Participacao da pos-graduacao: " + (posgraduandos / total) * 100 + "%" + NL +
				"% Participacao de profissionais: " + (profissionais / total) * 100 + "%" + NL;
		
		return str;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
