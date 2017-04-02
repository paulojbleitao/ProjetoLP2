package projeto;

import java.time.LocalDate;

public class Monitoria extends Projeto {

	private static final long serialVersionUID = 1L;
	private String disciplina;
	private double despesasConstantes;
	private int rendimento;

	public Monitoria(String nome, String disciplina, String objetivo, int rendimento, LocalDate dataInicio, int duracao,
			int codigo) throws Exception {
		super(nome, objetivo, dataInicio, duracao, codigo);
		this.despesasConstantes = 0;
		this.disciplina = disciplina;
		this.rendimento = rendimento;
		this.despesasConstantes = 0;
	}

	@Override
	public double calculaPontosGraduando() {
		return 1.5 * (this.getDuracao() / 6);
	}

	@Override
	public double calculaPontosProfessor() {
		return 4 * (this.getDuracao() / 12);
	}
	
	@Override
	public void atualizaDespesas(double montanteBolsas, double montanteCusteio, double montanteCapital) throws Exception {
		if (montanteCusteio > 0 || montanteCapital > 0)
			throw new Exception("Erro na atualizacao de projeto: projeto do tipo monitoria nao permite despesas de custeio ou capital");
		this.despesasConstantes = montanteBolsas;
	}
	
	@Override
	public double calculaColaboracao() {
		return 0;
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