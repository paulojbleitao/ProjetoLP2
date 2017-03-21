package pessoa;

import java.util.ArrayList;

import participacao.Graduando;
import participacao.Participacao;
<<<<<<< HEAD
import participacao.Professor;
import participacao.Profissional;
import participacao.TipoProfissional;
import projeto.Monitoria;
import projeto.PED;
import projeto.ProgramaInst;
=======
>>>>>>> 2f763809cbab7a43f0659cd7043223ec56f60958
import projeto.Projeto;
import projeto.TipoProgramaInst;

public class Pessoa {

	private String nome;
	private String cpf;
	private String email;
	private ArrayList<Participacao> participacoes;
	private double valorBolsa;

	public Pessoa(String nome, String cpf, String email) throws Exception {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.participacoes = new ArrayList<>();
		this.valorBolsa = 0.0;
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
		valorBolsa += participacao.calculaValorBolsa();
		if ((participacao.getProjeto() instanceof ProgramaInst && ((ProgramaInst) participacao.getProjeto()).getTipoPI() == TipoProgramaInst.PIVIC)) {
			valorBolsa = 0;
		}
		participacoes.add(participacao);
	}

	public void removeParticipacao(String codigo) {
		for (Participacao participacao : participacoes) {
			if (participacao.getProjeto().getCodigo().equals(codigo)) {
				valorBolsa -= participacao.calculaValorBolsa();
				participacoes.remove(participacao);
				break;
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

	public double calculaPontos() {
		double pontos = 0;
		double pontosMonitoria = 0;
		double pontosProjetos = 0;
		for (Participacao p : this.participacoes) {
			double temp = p.calculaPontos();
			if (p.getClass() == Graduando.class) {
				if (p.isMonitoria()) {
					pontosMonitoria += temp;
					if (pontosMonitoria > 6)
						pontosMonitoria = 6;
				} else {
					pontosProjetos += temp;
					if (pontosProjetos > 8)
						pontosProjetos = 8;
				}
			} else {
				pontos += temp;
			}
		}
		pontos += pontosMonitoria + pontosProjetos;
		return pontos;
	}

	public double getValorBolsa() {
		return valorBolsa;
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
