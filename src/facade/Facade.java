package facade;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import gerenciador.GerenciadorParticipacao;
import gerenciador.GerenciadorPessoa;
import gerenciador.GerenciadorProjeto;
import pessoa.Pessoa;
import projeto.Projeto;

public class Facade implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciadorPessoa gPessoa;
	private GerenciadorProjeto gProjeto;
	private GerenciadorParticipacao gParticipacao;

	public Facade() {
		gPessoa = new GerenciadorPessoa();
		gProjeto = new GerenciadorProjeto();
		gParticipacao = new GerenciadorParticipacao();
	}

	public void iniciaSistema() throws FileNotFoundException, IOException, ClassNotFoundException {
		 ObjectInputStream in = null;
	      try {
	         in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("arquivos_sistema/cpc_ufcg.dat")));
	         Facade f = (Facade) in.readObject();
	         gPessoa = f.gPessoa;
	         gProjeto = f.gProjeto;
	         gParticipacao = f.gParticipacao;
	      } finally {
	    	  if (in != null)
	    		  in.close();
	      }
	}

	public void fechaSistema() throws IOException {
		ObjectOutputStream out = null;
	    try {
	       out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("arquivos_sistema/cpc_ufcg.dat")));
	       out.writeObject(this);
	    } finally {
	    	if (out != null)
	    		out.close();
	    }
	}

	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		return gPessoa.cadastraPessoa(cpf, nome, email);
	}

	public void editaPessoa(String cpf, String atributo, String valor) throws Exception {
		gPessoa.editaPessoa(cpf, atributo, valor);
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		return gPessoa.getInfoPessoa(cpf, atributo);
	}

	public void removePessoa(String cpf) throws Exception {
		gPessoa.removePessoa(cpf);
	}

	public int adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		return gProjeto.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao);
	}

	public int adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		return gProjeto.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes,
				dataInicio, duracao);
	}

	public int adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		return gProjeto.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}

	public int adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		return gProjeto.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio,
				duracao);
	}

	public int getCodigoProjeto(String nome) throws Exception {
		int codigo = gProjeto.getCodigoProjeto(nome);
		if (codigo != 0)
			return codigo;
		else
			throw new Exception("Erro na obtencao de codigo de projeto: Projeto nao encontrado");
	}

	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		return gProjeto.getInfoProjeto(codigo, atributo);
	}

	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		gProjeto.editaProjeto(codigo, atributo, valor);
	}

	public void removeProjeto(String codigo) throws Exception {
		gProjeto.removeProjeto(codigo);
	}

	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras)
			throws Exception {
		Pessoa pessoa;
		Projeto projeto;
		try {
			pessoa = gPessoa.buscaPessoa(cpfPessoa);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = gProjeto.buscaProjeto(codigoProjeto);

		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}

		if (valorHora < 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		gParticipacao.associaGraduando(pessoa, projeto, valorHora, qntHoras);
	}

	public void associaPosGraduando(String cpfPessoa, String codigoProjeto, String nivel, double valorHora,
			int qntHoras) throws Exception {
		Pessoa pessoa;
		Projeto projeto;
		try {
			pessoa = gPessoa.buscaPessoa(cpfPessoa);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = gProjeto.buscaProjeto(codigoProjeto);

		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}

		if (valorHora <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		gParticipacao.associaPosGraduando(pessoa, projeto, nivel, valorHora, qntHoras);
	}

	public void associaProfessor(String cpfPessoa, String codigoProjeto, boolean coordenador, double valorHora,
			int qntHoras) throws Exception {
		Pessoa pessoa;
		Projeto projeto;
		try {
			pessoa = gPessoa.buscaPessoa(cpfPessoa);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = gProjeto.buscaProjeto(codigoProjeto);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}

		if (valorHora < 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		gParticipacao.associaProfessor(pessoa, projeto, coordenador, valorHora, qntHoras);
	}

	public void associaProfissional(String cpfPessoa, String codigoProjeto, String cargo, double valorHora,
			int qntHoras) throws Exception {
		Pessoa pessoa;
		Projeto projeto;
		try {
			pessoa = gPessoa.buscaPessoa(cpfPessoa);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = gProjeto.buscaProjeto(codigoProjeto);

		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Projeto nao encontrado");
		}

		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}

		if (valorHora <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		gParticipacao.associaProfissional(pessoa, projeto, cargo, valorHora, qntHoras);

	}

	public void removeParticipacao(String cpfPessoa, String codigoProjeto) throws Exception {
		Pessoa pessoa;
		Projeto projeto;
		try {
			pessoa = gPessoa.buscaPessoa(cpfPessoa);

		} catch (Exception e) {
			throw new Exception("Erro na remocao de participacao: Pessoa nao encontrada");
		}
		try {
			projeto = gProjeto.buscaProjeto(codigoProjeto);

		} catch (Exception e) {
			throw new Exception("Erro na remocao de participacao: Projeto nao encontrado");
		}

		if (projeto.buscaParticipacao(pessoa) == null) {
			throw new Exception("Erro na remocao de participacao: Pessoa nao possui participacao no projeto indicado");
		}

		gParticipacao.removeParticipacao(pessoa, projeto);
	}

	public double calculaPontuacaoPorParticipacao(String cpf) throws Exception {
		return gPessoa.calculaPontuacao(cpf);
	}

	public double getValorBolsa(String cpf) throws Exception {
		return gPessoa.getValorBolsa(cpf);
	}

	public void atualizaDespesasProjeto(String codigo, double montanteBolsas, double montanteCusteio,
			double montanteCapital) throws Exception {
		gProjeto.atualizaDespesasProjeto(codigo, montanteBolsas, montanteCusteio, montanteCapital);
	}

	public double calculaColaboracaoUASC(String codigo) throws Exception {
		return gProjeto.calculaColaboracao(codigo);
	}

	public void diminuiReceita(double valor) throws Exception {
		gProjeto.diminuiReceita(valor);
	}

	public double calculaColaboracaoTotalUASC() {
		return gProjeto.getColaboracao();
	}

	public double calculaTotalEmCaixaUASC() {
		return gProjeto.getTotalEmCaixa();
	}

	public void salvaProjetos() throws IOException {
		FileWriter fw = null;
		BufferedWriter bfw = null;
		try {
			fw = new FileWriter("arquivos_sistema/relatorios/cad_projetos.txt");
			bfw = new BufferedWriter(fw);
			bfw.write(gProjeto.toString());
		} finally {
			if (bfw != null)
				bfw.close();
		}
	}

	public void salvaColaboracoes() throws IOException {
		FileWriter fw = null;
		BufferedWriter bfw = null;
		try {
			fw = new FileWriter("arquivos_sistema/relatorios/cad_colaboracoes.txt");
			bfw = new BufferedWriter(fw);
			bfw.write(gProjeto.historicoColaboracoes());
		} finally {
			if (bfw != null)
				bfw.close();
		}
	}

}
