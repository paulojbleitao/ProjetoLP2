package producao;

public class Producao {

	private Produtividade produtividade;
	private int quant;

	public Producao(Produtividade produtividade, int quant) {
		this.produtividade = produtividade;
		this.quant = quant;

	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public Produtividade getProdutividade() {
		return produtividade;
	}

}
