package br.com.escriba.model;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;



@Entity
public class GrupoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nome;
    
  

	public GrupoProduto(long id, String nome) {
    	this.id = id;
    	this.nome = nome;
    }

    public GrupoProduto() {}
    
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
