package br.com.escriba.model;

import java.io.Serializable;

public class ProdutoId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private NotaFiscal notaFiscal;
     
	public ProdutoId() { }
	
	public ProdutoId(Produto produto, NotaFiscal notaFiscal) {
		this.produto = produto;
		this.notaFiscal = notaFiscal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
     
	
    
}