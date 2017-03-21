
package projeto;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import participacao.Graduando;
import participacao.Participacao;
import participacao.Professor;
import pessoa.Pessoa;

public abstract class Projeto {

	private String nome;
	private String objetivo;
	private LocalDate dataInicio;
	private int duracao;
	private String codigo;
	protected List<Participacao> participacoes;

	public Projeto(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo) throws Exception {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
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
			if (participacao instanceof Graduando)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Projeto))
			return false;
		Projeto other = (Projeto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}