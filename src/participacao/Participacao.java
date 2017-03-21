package participacao;

import java.time.LocalDate;

import pessoa.Pessoa;
import projeto.Projeto;

public abstract class Participacao implements Comparable<Participacao> {

	protected Pessoa pessoa;
	protected Projeto projeto;
	protected LocalDate dataInicio;
	protected int duracao;
	protected int horasSemanais;
	protected double valorHora;

	public Participacao(Pessoa pessoa, Projeto projeto, LocalDate dataInicio, int duracao, int horasSemanais,
			double valorHora) {
		this.pessoa = pessoa;
		this.projeto = projeto;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.horasSemanais = horasSemanais;
		this.valorHora = valorHora;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public int getHorasSemanais() {
		return horasSemanais;
	}

	public double getValorHora() {
		return valorHora;
	}

	public abstract void calculaValorBolsa() throws Exception;

	@Override
	public int compareTo(Participacao p) {
		return this.pessoa.getNome().compareTo(p.getPessoa().getNome());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Participacao))
			return false;
		Participacao other = (Participacao) obj;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		return true;
	}

}
