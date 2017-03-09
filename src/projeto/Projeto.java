package projeto;

import java.time.*;

public class Projeto {

	private String nome;
	private String objetivo;
	private LocalDate dataInicio;
	private int duracao;
	private String codigo;

	public Projeto(String nome, String objetivo, LocalDate dataInicio, int duracao, String codigo) throws Exception {
		if (nome == null || nome.trim().equals("")) {
			throw new Exception("Erro no cadastro de projeto: Nome nulo ou vazio");
		}
		if (objetivo == null || objetivo.trim().equals("")) {
			throw new Exception("Erro no cadastro de projeto: Objetivo nulo ou vazio");
		}
		if (dataInicio == null || dataInicio.equals("")) { 
			throw new Exception("Erro no cadastro de projeto: Data de inicio nula ou vazia");
		}
		if (duracao <= 0) {
			throw new Exception("Erro no cadastro de projeto: Duracao invalida");
		}
		if (codigo == null || codigo.trim().equals("")) {
			throw new Exception("Erro no cadastro de projeto: Codigo nulo ou vazio");
		}
		this.nome = nome;
		this.objetivo = objetivo;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.codigo = codigo;
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
