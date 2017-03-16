package pessoa;

import java.util.ArrayList;

import participacao.PosGraduando;
import participacao.Participacao;
import participacao.Professor;
import projeto.Monitoria;
import projeto.Projeto;

public class Pessoa {

	private String nome;
	private String cpf;
	private String email;
	private ArrayList<Participacao> participacoes;
	private double pontos;

	public Pessoa(String nome, String cpf, String email) throws Exception {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.participacoes = new ArrayList<>();
		this.pontos = 0.0;
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

	public void addParticipacao(Participacao participacao) {
		participacoes.add(participacao);
	}

	public String participacoesDePessoa() {
		String resposta = "";
		for (Participacao participacao : participacoes) {
			if (resposta.equals("")) {
				resposta += participacao.getProjeto().getNome();
			} else {
				resposta += ", " + participacao.getProjeto().getNome();
			}
			
		}
		return resposta;

		/*StringBuilder resposta = new StringBuilder();
		for (Participacao participacao : participacoes) {
			resposta.append(participacao.getProjeto().getNome() + ", ");
		}
		resposta.deleteCharAt(resposta.length() - 1);
		return resposta.toString();
		
		// resposta = []
		// respota = ["primeiro projeto, ", "swegundo projeto, "]; 
		resposta.toString = primeiro projeto, segundo projeto,*/  
	}

	public void calculaPontos() {
		for (Participacao p : participacoes) {
			if (p instanceof Professor) {
				int temp = 4 * (p.getDuracao() / 12);
				if (!(p.getProjeto() instanceof Monitoria))
					temp += p.getProjeto().getQntdGraduandos();
				pontos += temp;
			} else if (p instanceof PosGraduando) {
				// TODO aaaaaaaaaaaaaaaaaaaaaa
			}
		}
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
