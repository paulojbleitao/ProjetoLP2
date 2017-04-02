package producao;

import java.io.Serializable;

public class Producao implements Serializable {

	private static final long serialVersionUID = 1L;
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
