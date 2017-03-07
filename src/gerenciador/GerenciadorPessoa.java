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
	
	public void cadastraPessoa(String cpf, String nome, String email) {
		pessoas.add(new Pessoa(cpf, nome, email));
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
