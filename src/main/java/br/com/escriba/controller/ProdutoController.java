package br.com.escriba.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.escriba.exception.ResourceNotFoundException;
import br.com.escriba.model.Produto;
import br.com.escriba.repository.ProdutoRepository;
import java.util.List;


import javax.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
 

	@GetMapping("/listAll")
	public @ResponseBody List<Produto> getAllProdutos() {
		return produtoRepository.findAll();
	}

	// Extra método usado para incluir produto
	@PostMapping("/")
	public @ResponseBody  Produto createProduto(@Valid @RequestBody Produto produto) {
		return produtoRepository.save(produto);
	} 

	// Extra método para buscar produto por id
	@GetMapping("/{id}" )
	public @ResponseBody Produto getProduto(@PathVariable(value = "id") Long produtoId) {
		return produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Produto", "id", produtoId));
	}

	// Extra método para atualizar produto  por id
	@PutMapping("/{id}")
	public @ResponseBody Produto updateProduto(@PathVariable(value = "id") Long produtoId,
			@Valid @RequestBody Produto produtoAntigo) {
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Produto", "id", produtoId));

		produto.setNome(produtoAntigo.getNome());
		produto.setValorUnitario(produtoAntigo.getValorUnitario());

		return produtoRepository.save(produto);
	}
	
	


}
