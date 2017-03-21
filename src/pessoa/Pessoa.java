package pessoa;

import java.util.ArrayList;

import participacao.Graduando;
import participacao.Participacao;
import participacao.Professor;
import participacao.Profissional;
import participacao.TipoProfissional;
import projeto.Monitoria;
import projeto.PED;
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
	

	public void removeParticipacao(String codigo) {
		for (Participacao participacao : participacoes) {
			if (participacao.getProjeto().getCodigo().equals(codigo)) {
				participacoes.remove(participacao);
				return;
			}			
		} 
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
	}
/*
	public double calculaPontos() {
		double pontosMonitoria = 0;
		double pontosProjetos = 0;
		for (Participacao p: this.participacoes) {
			if (p.isMonitoria())
				duracaoMonitoria += p.getDuracao();
			else
				duracaoProjetos += p.getDuracao();
			
			
			pontos += p.calculaPontos();
			if (p instanceof Professor) {
				int temp = 4 * (p.getDuracao() / 12);
				if (!(p.getProjeto() instanceof Monitoria))
					temp += p.getProjeto().getQntdGraduandos();
				this.pontos += temp;
			} else if (p instanceof Graduando) {
				double temp = 0.0;
				if (p.getProjeto() instanceof Monitoria) {
					duracaoMonitoria += p.getDuracao();
					if (duracaoMonitoria >= 24)
						temp += 6;
					else
						temp += 1.5 * (p.getDuracao() / 6);
				} else {
					duracaoProjetos += p.getDuracao();
					if (duracaoProjetos >= 24)
						temp += 8;
					else
						temp += 2 * (p.getDuracao() / 6);
				}
				this.pontos += temp;
			} else if (p instanceof Profissional) {
				int temp = 0;
				if (p.getProjeto() instanceof PED) {
					if (((Profissional) p).getTipoProfissional() == TipoProfissional.DESENVOLVEDOR)
						temp += 5 * (p.getDuracao() / 12);
					else if (((Profissional) p).getTipoProfissional() == TipoProfissional.PESQUISADOR)
						temp += 6 * (p.getDuracao() / 12);
					else if (((Profissional) p).getTipoProfissional() == TipoProfissional.GERENTE)
						temp += 9 * (p.getDuracao() / 12);
				}
				pontos += temp;
			}
		}
		return this.pontos;
	}
*/
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
