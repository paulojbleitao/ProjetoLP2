package pessoa;

import java.util.ArrayList;
import participacao.Participacao;
import projeto.Projeto;

public class Pessoa {

	private String nome;
	private String cpf;
	private String email;
	private ArrayList<Participacao> participacoes;

	public Pessoa(String nome, String cpf, String email) throws Exception {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) throws Exception {
		this.email = email;
	}

	public Participacao buscaParticipacao(Projeto projeto) {
		for (Participacao participacao : participacoes) {
			if (participacao.getProjeto().equals(projeto)) {
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
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
