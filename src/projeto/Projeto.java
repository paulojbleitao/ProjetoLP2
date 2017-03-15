package projeto;

import java.time.*;
import java.util.ArrayList;

import participacao.Participacao;
import pessoa.Pessoa;

public abstract class Projeto {

	private String nome;
	private String objetivo;
	private LocalDate dataInicio;
	private int duracao;
	private String codigo;
	protected ArrayList<Participacao> participacoes;

	public Projeto(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo) throws Exception {
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.codigo = codigo;
		participacoes = new ArrayList<>();
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
	
	public Participacao buscaParticipacao(Pessoa pessoa) {
		for (Participacao participacao : participacoes) {
			if (participacao.getProjeto().equals(pessoa)) {
				return participacao;
			}
		}
		return null;
	}
	
	public void addParticipacao (Participacao participacao) {
		participacoes.add(participacao);
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
