package gerenciador;

import java.time.LocalDate;

import factory.FactoryParticipacao;
import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;

public class GerenciadoParticipacao {
	
	private GerenciadorPessoa gerentePessoa;
	private GerenciadorProjeto gerenteProjeto;
	private FactoryParticipacao factoryParticipacao;
	
	public void associaGraduando(String cpfPessoa, String codigoProjeto, double valorHora, int qntHoras) throws Exception {
		Pessoa pessoa;
		Projeto projeto;
		try {
			pessoa = gerentePessoa.buscaPessoa(cpfPessoa);
		} catch (Exception e) {
			throw new Exception("Erro na associacao de pessoa a projeto: Pessoa nao encontrada");
		}
		try {
			projeto = gerenteProjeto.buscaProjeto(codigoProjeto);
			
		} catch  (Exception e) {
			throw new Exception("Erro na obtencao de codigo de projeto: Projeto nao encontrado");
		}
		if (qntHoras <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}
		if (valorHora <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		Participacao participacao = factoryParticipacao.criaGraduando(pessoa, projeto, projeto.getDataInicio(), projeto.getDuracao(), qntHoras, valorHora, );
		
	}

}
