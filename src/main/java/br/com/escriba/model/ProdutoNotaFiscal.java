package br.com.escriba.model;



import javax.persistence.*;
import com.sun.istack.NotNull;



@Entity

public class ProdutoNotaFiscal {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota_fiscal_id", referencedColumnName = "id")
    private NotaFiscal notaFiscal;

	
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

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Column(nullable = false)
    @NotNull
    private int quantidade;

   

	@Column(nullable = false)
    @NotNull
    private double valorUnitario;
	
	@Column(nullable = false)
    @NotNull
    private double valorTotal;
    

    public ProdutoNotaFiscal() {}

    public ProdutoNotaFiscal(Produto produto, NotaFiscal notaFiscal, int quantidade, double valorUnitario, double valorTotal) {
    	this.produto = produto;
    	this.notaFiscal = notaFiscal;
    	this.quantidade = quantidade;
    	this.valorUnitario = valorUnitario;
    	this.valorTotal = valorTotal;
    }

}


