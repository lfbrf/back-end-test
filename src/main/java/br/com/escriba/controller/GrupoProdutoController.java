package br.com.escriba.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.escriba.exception.ResourceNotFoundException;
import br.com.escriba.model.GrupoProduto;
import br.com.escriba.repository.GrupoProdutoRepository;
import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/grupoProdutos")
public class GrupoProdutoController {

	@Autowired
	GrupoProdutoRepository grupoProdutoRepository;


	@GetMapping("/listAll")
	public @ResponseBody List<GrupoProduto> getAllGruposProdutos() {
		return grupoProdutoRepository.findAll();
	}
	// Extra, método não solicitado mas desenvolvido para aumentar a implementação
	@GetMapping("/findLike/{nome}")
	public @ResponseBody List<GrupoProduto> getFindLike(@PathVariable(value = "nome") String nome) {
		return grupoProdutoRepository.findLikeNome(nome);
	}
	// Extra, método não solicitado mas desenvolvido para aumentar a implementação
	// Busca o nome do grupo com maior ocorrência da palavra passada por parâmetro
	// Caso a maior ocorrência tenha um ou vários empates é retornado o primeiro da ordem decrescente
	@GetMapping("/findMatchLetter/{letra}")
	public @ResponseBody GrupoProduto findMatchLetter(@PathVariable(value = "letra") String letra) {
		return grupoProdutoRepository.findMatchLetter(letra);
	}

	// Extra Usado para Paginação, inicio começa em zero
	@GetMapping("/listAll/")
	@ResponseBody 
	public  List <GrupoProduto> paginacao(@RequestParam int inicio, @RequestParam int quantidade) {
		return grupoProdutoRepository.pagination(quantidade, inicio);
	}

	// Extra método usado para incluir grupos
	@PostMapping("/")
	public @ResponseBody  GrupoProduto createGrupoProduto(@Valid @RequestBody GrupoProduto grupoProduto) {
		return grupoProdutoRepository.save(grupoProduto);
	}

	// Extra método para buscar grupo por id
	@GetMapping("/{id}" )
	public @ResponseBody GrupoProduto getGrupoProduto(@PathVariable(value = "id") Long grupoProdutoId) {
		return grupoProdutoRepository.findById(grupoProdutoId)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", grupoProdutoId));
	}

	// Extra método para atualizar grupo produto por id
	@PutMapping("/{id}")
	public @ResponseBody GrupoProduto updateProduto(@PathVariable(value = "id") Long grupoProdutoId,
			@Valid @RequestBody GrupoProduto grupoProdutoAntigo) {
		GrupoProduto grupoProduto = grupoProdutoRepository.findById(grupoProdutoId)
				.orElseThrow(() -> new ResourceNotFoundException("Grupo", "id", grupoProdutoId));

		grupoProduto.setNome(grupoProdutoAntigo.getNome());

		return  grupoProdutoRepository.save(grupoProduto);
	}

	

}
