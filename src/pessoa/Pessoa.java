package pessoa;

public class Pessoa {

	private String nome;
	private String cpf;
	private String email;

	public Pessoa(String nome, String cpf, String email) throws Exception {
		if (nome == null || nome.trim().equals(""))
			throw new Exception("Erro no cadastro de pessoa: Nome nulo ou vazio");
		else if (cpf == null || cpf.trim().equals(""))
			throw new Exception("Erro no cadastro de pessoa: CPF nulo ou vazio");
		else if (email == null || email.trim().equals(""))
			throw new Exception("Erro no cadastro de pessoa: Email nulo ou vazio");
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
