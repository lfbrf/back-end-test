package br.com.escriba.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;



@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    private GrupoProduto grupoProduto;

	

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	@Column(nullable = false)
    @NotNull
    private double valorUnitario;

    public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Column(nullable = false)
    @NotBlank
    private String nome;
    
    public Produto(long id, String nome) {
    	this.id = id;
    	this.nome = nome;
    }

    public Produto() {}
    
    public Produto(long id, double valorUnitario, String nome, GrupoProduto grupoProduto ) {
    	this.id = id;
    	this.valorUnitario = valorUnitario;
    	this.nome = nome;
    	this.grupoProduto = grupoProduto;
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
