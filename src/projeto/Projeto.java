
package projeto;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import participacao.Graduando;
import participacao.Participacao;
import participacao.PosGraduando;
import participacao.Professor;
import participacao.Profissional;
import pessoa.Pessoa;

public abstract class Projeto implements Comparable<Projeto>, Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String objetivo;
	private LocalDate dataInicio;
	private int duracao;
	private int codigo;
	protected List<Participacao> participacoes;

	public Projeto(String nome, String objetivo, LocalDate dataInicio, int duracao, int codigo) throws Exception {
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.codigo = codigo;
		this.participacoes = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	public Participacao buscaParticipacao(Pessoa pessoa) {
		for (Participacao participacao : participacoes) {
			if (participacao.getPessoa().equals(pessoa)) {
				return participacao;
			}
		}
		return null;
	}

	public boolean contem(Pessoa pessoa) {
		for (Participacao participacao : participacoes) {
			if (participacao.getPessoa().equals(pessoa)) {
				return true;
			}
		}
		return false;
	}

	public boolean contemProfessor() {
		for (Participacao participacao : participacoes) {
			if (participacao instanceof Professor) {
				return true;
			}
		}
		return false;
	}

	public boolean contemCoordenador() {
		for (Participacao participacao : participacoes) {
			if (participacao instanceof Professor) {
				if (((Professor) participacao).isCoordenador()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean contemGraduando() {
		for (Participacao participacao : participacoes) {
			if (participacao instanceof Graduando) {
				return true;
			}
		}
		return false;
	}

	public int getQntdGraduandos() {
		int qntd = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.getClass() == Graduando.class || participacao.getClass() == PosGraduando.class)
				qntd++;
		}
		return qntd;
	}

	public int getQntdPosGraduandos() {
		int qntd = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.getClass() == PosGraduando.class)
				qntd++;
		}
		return qntd;
	} 
	
	public int getQntdProfissionais() {
		int qntd = 0;
		for (Participacao participacao : participacoes) {
			if (participacao.getClass() == Profissional.class)
				qntd++;
		}
		return qntd;
	} 
	
	public void addParticipacao(Participacao participacao) throws Exception {
		if (buscaParticipacao(participacao.getPessoa()) != null) {
			throw new Exception("Erro na associacao de pessoa a projeto: Aluno ja esta cadastrado nesse projeto");
		}
		participacoes.add(participacao);
	}

	public void removeParticipacao(String cpfPessoa) {
		for (Participacao participacao : participacoes) {
			if (participacao.getPessoa().getCpf().equals(cpfPessoa)) {
				participacoes.remove(participacao);
				return;
			}
		}
	}

	public String participacoesDeProjeto() {
		String resposta = "";
		participacoes.sort(null);

		for (Participacao participacao : participacoes) {
			if (resposta.equals(""))
				resposta += participacao.getPessoa().getNome();
			else
				resposta += ", " + participacao.getPessoa().getNome();
		}
		return resposta;
	}

	private Pessoa getCoordenador() {
		for (Participacao participacao : participacoes) {
			if (participacao.getClass() == Professor.class && ((Professor) participacao).isCoordenador()) {
				return participacao.getPessoa();
			}
		}
		try {
			return new Pessoa("Ninguem", "123.345.678-90", "ninguem@asdf.com");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getSituacao() {
		if (isFinalizado())
			return "Finalizado";
		else 
			return "Em andamento";
	}

	public boolean isFinalizado() {
		if (this.dataInicio.plusMonths(duracao).compareTo(LocalDate.now()) <= 0)
			return true;
		else
			return false;
	}
	
	public double calculaPontosGraduando() {
		return 2 * (this.duracao / 6);
	}

	public double calculaPontosProfessor() {
		double temp = 4 * (this.duracao / 12);
		temp += this.getQntdGraduandos();
		return temp;
	}

	public abstract void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital)
			throws Exception;

	public abstract double calculaColaboracao();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		final String NL = System.lineSeparator();
		String str = "Nome: " + this.nome + NL +
				"Data de inicio: " + this.dataInicio.toString() + NL +
				"Coordenador: " + this.getCoordenador().getNome() + NL +
				"Situacao: " + this.getSituacao() + NL;
		return str;
	}
	
	@Override
	public int compareTo(Projeto p) {
		return Integer.compare(this.codigo, p.codigo);
	}

}