package projeto;

import java.time.LocalDate;

public class Monitoria extends Projeto {

	private String disciplina;
	private double despesasConstantes;
	private int rendimento;

	public Monitoria(String nome, String disciplina, String objetivo, int rendimento, LocalDate dataInicio, int duracao,
			String codigo) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.despesasConstantes = 0;
		if (disciplina == null || disciplina.trim().equals("")) {
			throw new Exception ("Erro no cadastro da disciplina: Disciplina nulo ou vazia");
		}
		if (rendimento < 0 || rendimento > 100) {
			throw new Exception("Erro no cadastro de projeto: Rendimento invalido");
		}
		this.disciplina = disciplina;
		this.rendimento = rendimento;
		this.despesasConstantes = 0;
	}

	public double getDespesasConstantes() {
		return despesasConstantes;
	}

	public void setDespesasConstantes(double despesasConstantes) {
		this.despesasConstantes = despesasConstantes;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public int getRendimento() {
		return rendimento;
	}

	public void setRendimento(int rendimento) {
		this.rendimento = rendimento;
	}

}
