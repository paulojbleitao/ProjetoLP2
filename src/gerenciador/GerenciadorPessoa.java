package gerenciador;

import java.util.HashSet;

import pessoa.Pessoa;

public class GerenciadorPessoa {

	HashSet<Pessoa> pessoas;
	
	private Pessoa buscaPessoa(String cpf) throws Exception {
		for (Pessoa p: pessoas) {
			if (p.getCpf().equals(cpf))
				return p;
		}
		throw new Exception("Erro na consulta de pessoa: Pessoa nao encontrada");
	}
	
	public GerenciadorPessoa() {
		pessoas = new HashSet<>();
	}
	
	public void iniciaSistema() {}
	public void fechaSistema() {}
	
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
		if (this.validaEmail(email) == false)
			throw new Exception("Erro no cadastro de pessoa: Email invalido");
		if (this.validaCpf(cpf) == false)
			throw new Exception("Erro no cadastro de pessoa: CPF invalido");
		Pessoa p = new Pessoa(nome, cpf, email);
		if (pessoas.contains(p))
			throw new Exception("Erro no cadastro de pessoa: Pessoa com mesmo CPF ja cadastrada");
		pessoas.add(p);
		return cpf;
	}
	
	public void editaPessoa(String cpf, String atributo, String valor) throws Exception {
		Pessoa p = this.buscaPessoa(cpf);
		if (atributo.equalsIgnoreCase("nome"))
			p.setNome(valor);
		else if (atributo.equalsIgnoreCase("email"))
			p.setEmail(valor);
	}
	
	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		Pessoa p = this.buscaPessoa(cpf);
		if (atributo.equalsIgnoreCase("nome"))
			return p.getNome();
		else if (atributo.equalsIgnoreCase("email"))
			return p.getEmail();
		return null;
	}
	
	public void removePessoa(String cpf) throws Exception {
		Pessoa p = this.buscaPessoa(cpf);
		pessoas.remove(p);
	}
	
}
