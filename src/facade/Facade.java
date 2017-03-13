package facade;

import easyaccept.EasyAccept;
import gerenciador.GerenciadorPessoa;
import gerenciador.GerenciadorProjeto;

public class Facade {
	
	private GerenciadorPessoa gPessoa;
	private GerenciadorProjeto gProjeto;
	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "acceptance_tests/us1_test.txt", "acceptance_tests/us1_test_exception.txt",
	    		"acceptance_tests/us2_test.txt", "acceptance_tests/us2_test_exception.txt"};
	    EasyAccept.main(args);
	}
	
	public Facade() {
		gPessoa = new GerenciadorPessoa();
		gProjeto = new GerenciadorProjeto();
	}
	
	public void iniciaSistema() {}
	public void fechaSistema() {}
	
	public String cadastraPessoa(String cpf, String nome, String email) throws Exception {
		return gPessoa.cadastraPessoa(cpf, nome, email);
	}
	
	public void editaPessoa(String cpf, String atributo, String valor) throws Exception {
		gPessoa.editaPessoa(cpf, atributo, valor);
	}

	public String getInfoPessoa(String cpf, String atributo) throws Exception {
		return gPessoa.getInfoPessoa(cpf, atributo);
	}
	
	public void removePessoa(String cpf) throws Exception {
		gPessoa.removePessoa(cpf);
	}
	
	public String adicionaMonitoria(String nome, String disciplina, int rendimento, String objetivo, String periodo,
			String dataInicio, int duracao) throws Exception {
		return gProjeto.adicionaMonitoria(nome, disciplina, rendimento, objetivo, periodo, dataInicio, duracao);
	}

	public String adicionaPET(String nome, String objetivo, int impacto, int rendimento, int prodTecnica,
			int prodAcademica, int patentes, String dataInicio, int duracao) throws Exception {
		return gProjeto.adicionaPET(nome, objetivo, impacto, rendimento, prodTecnica, prodAcademica, patentes, dataInicio, duracao);
	}
	
	public String adicionaExtensao(String nome, String objetivo, int impacto, String dataInicio, int duracao)
			throws Exception {
		return gProjeto.adicionaExtensao(nome, objetivo, impacto, dataInicio, duracao);
	}
	
	public String adicionaPED(String nome, String categoria, int prodTecnica, int prodAcademica, int patentes,
			String objetivo, String dataInicio, int duracao) throws Exception {
		return gProjeto.adicionaPED(nome, categoria, prodTecnica, prodAcademica, patentes, objetivo, dataInicio, duracao);
	}
	
	public String getCodigoProjeto(String nome) {
		return gProjeto.getCodigoProjeto(nome);
	}
	
	public String getInfoProjeto(String codigo, String atributo) throws Exception {
		return gProjeto.getInfoProjeto(codigo, atributo);
	}
	
	public void editaProjeto(String codigo, String atributo, String valor) throws Exception {
		gProjeto.editaProjeto(codigo, atributo, valor);
	}
	
	public void removeProjeto(String codigo) throws Exception {
		gProjeto.removeProjeto(codigo);
	}
	
}
