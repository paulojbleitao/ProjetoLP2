package gerenciador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import participacao.FactoryParticipacao;
import participacao.Participacao;
import pessoa.Pessoa;
import projeto.Projeto;

public class GerenciadorParticipacoes {
	
	ArrayList<Participacao> participacoes;
	FactoryParticipacao factoryParticipacao;
	GerenciadorPessoa gerenPessoa;
	GerenciadorProjeto gerenProjeto;
		
	public void associaProfessor (String cpf, String codigo , int horasSemanais, double valorHora, boolean coordenador) throws Exception {
		if (horasSemanais <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Quantidade de horas invalida");
		}
		if (valorHora <= 0) {
			throw new Exception("Erro na associacao de pessoa a projeto: Valor da hora invalido");
		}
		Participacao prof;
		Projeto projeto = gerenProjeto.buscaProjeto(codigo);
		Pessoa pessoa = gerenPessoa.buscaPessoa(cpf);
		if (projeto.getClass().getName().equals("Monitoria")) {
			if (participacoes.contains(o))
			}
		}
		prof = factoryParticipacao.criaProfessor(pessoa, projeto, projeto.getDataInicio(), projeto.getDuracao(), horasSemanais, valorHora, coordenador);
		
		
		
		}
	
	}

