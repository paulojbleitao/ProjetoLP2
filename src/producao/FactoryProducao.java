package producao;

public class FactoryProducao {

	public Producao criaProducao(String produtividade, int quant) throws Exception {
		if (produtividade.trim().equalsIgnoreCase("producaoTecnica")) {
			Producao prodTecnica = new Producao(Produtividade.PRODUCAO_TECNICA, quant);
			return prodTecnica;
		} else if (produtividade.trim().equalsIgnoreCase("producaoAcademica")) {
			Producao prodAcademica = new Producao(Produtividade.PRODUCAO_ACADEMICA, quant);
			return prodAcademica;
		} else if (produtividade.trim().equalsIgnoreCase("patentes")) {
			Producao patentes = new Producao(Produtividade.PATENTES, quant);
			return patentes;
		} else {
			throw new Exception("Erro no cadastro de producao: Produtividade invalida");
		}

	}

}
