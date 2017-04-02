package gerenciador;

import java.io.Serializable;
import java.util.HashSet;

import pessoa.Pessoa;

public class GerenciadorPessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	HashSet<Pessoa> pessoas;
	
	public GerenciadorPessoa() {
		pessoas = new HashSet<>();
	}
	
	public Pessoa buscaPessoa(String cpf) throws Exception {
		for (Pessoa p: pessoas) {
			if (p.getCpf().equals(cpf))
				return p;
		}
		throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
	}
	
	private boolean validaEmail(String email) {
		int arrobas = 0;
		int pontos = 0;
		if (email.charAt(0) == '@' || email.charAt(email.length()-1) == '@' || email.charAt(email.length()-1) == '.')
			return false;
			
		for (int i = 0; i < email.length() - 1; i++) {
			if (email.charAt(i) == ' ') {
				return false;
			} else if (email.charAt(i) == '@') {
				arrobas++;
				if (email.charAt(i+1) == '@')
					return false;
			} else if (email.charAt(i) == '.' && arrobas == 1) {
				pontos++;
				if (email.charAt(i+1) == '.')
					return false;
			}
		}
		
		if (arrobas != 1 || pontos < 1)
			return false;
			
		return true;
	}
	
	private boolean validaCpf(String cpf) {
		if (cpf.length() != 14)
			return false;
		else if (cpf.charAt(3) != '.' || cpf.charAt(7) != '.' || cpf.charAt(11) != '-')
			return false;
		return true;
	}
	
	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		if (nome == null || nome.trim().equals(""))
			throw new Exception("Erro no cadastro de pessoa: Nome nulo ou vazio");
		else if (cpf == null || cpf.trim().equals(""))
			throw new Exception("Erro no cadastro de pessoa: CPF nulo ou vazio");
		else if (email == null || email.trim().equals(""))
			throw new Exception("Erro no cadastro de pessoa: Email nulo ou vazio");
		else if (this.validaEmail(email) == false)
			throw new Exception("Erro no cadastro de pessoa: Email invalido");
		else if (this.validaCpf(cpf) == false)
			throw new Exception("Erro no cadastro de pessoa: CPF invalido");
		Pessoa p = new Pessoa(nome, cpf, email);
		if (pessoas.contains(p))
			throw new Exception("Erro no cadastro de pessoa: Pessoa com mesmo CPF ja cadastrada");
		pessoas.add(p);
		return cpf;
	}
	
	public void editaPessoa(String cpf, String atributo, String valor) throws Exception {
		if (cpf == null || cpf.trim().equals(""))
			throw new Exception("Erro na atualizacao de pessoa: CPF nulo ou vazio");
		if (this.validaCpf(cpf) == false)
			throw new Exception("Erro na atualizacao de pessoa: CPF invalido");
		
		Pessoa p = this.buscaPessoa(cpf);
		if (atributo.equalsIgnoreCase("nome")) {
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de pessoa: Nome nulo ou vazio");
			p.setNome(valor);
		} else if (atributo.equalsIgnoreCase("email")) {
			if (valor == null || valor.trim().equals(""))
				throw new Exception("Erro na atualizacao de pessoa: Email nulo ou vazio");
			if (this.validaEmail(valor) == false)
				throw new Exception("Erro na atualizacao de pessoa: Email invalido");
			p.setEmail(valor);
		} else if (atributo.equalsIgnoreCase("cpf")) {
			throw new Exception("Erro na atualizacao de pessoa: CPF nao pode ser alterado");
		}
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		Pessoa p = this.buscaPessoa(cpf);
		switch (atributo.toUpperCase()) {
		case "NOME":
			return p.getNome();
			
		case "EMAIL":
			return p.getEmail();
			
		case "PARTICIPACOES":
			return p.participacoesDePessoa();
			
		default:
			break;
		}
		throw new Exception("Atributo invalido");
	}
	
	public void removePessoa(String cpf) throws Exception {
		Pessoa p = this.buscaPessoa(cpf);
		pessoas.remove(p);
	}

	public double calculaPontuacao(String cpf) throws Exception {
		Pessoa p = this.buscaPessoa(cpf);
		return p.calculaPontos();
	}
	
	public double getValorBolsa(String cpf) throws Exception {
		Pessoa p = this.buscaPessoa(cpf);
		return p.getValorBolsa();
	}
	
}
