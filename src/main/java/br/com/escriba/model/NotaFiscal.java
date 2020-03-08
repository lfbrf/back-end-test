package br.com.escriba.model;


import javax.persistence.*;
import com.sun.istack.NotNull;

@Entity
public class NotaFiscal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotNull
	private double valorTotal;

	// Tratando número como string para facilitar inclusão no banco em possíveis casos de NF com zero à esquerda
	// Não foi especificado se o mesmo deve ser único, considero que sim, caso não fosse bastaria alterar o unique aqui, o arquivo sql
	// e ajustar o findByNumero para retornar lista
	@Column(nullable = false, unique = true)
	@NotNull
	private String numero;

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public NotaFiscal() {}

	public NotaFiscal(long id, double valorTotal, String numero ) {
		this.id = id;
		this.valorTotal = valorTotal;
		this.numero = numero;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
