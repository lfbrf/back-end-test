package br.com.escriba.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.escriba.exception.ResourceNotFoundException;
import br.com.escriba.model.ProdutoNotaFiscal;
import br.com.escriba.repository.ProdutoNotaFiscalRepository;
import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/produtosNotaFiscal")
public class ProdutoNotaFiscalController {

    @Autowired
    ProdutoNotaFiscalRepository produtoNotaFiscalRepository;
    
    @GetMapping("/listAll")
    public @ResponseBody List<ProdutoNotaFiscal> getAllProdutoNotaFiscal() {
        return produtoNotaFiscalRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody ProdutoNotaFiscal getProdutoNF(@PathVariable(value = "id") Long produtoNotaFiscalId) {
        return produtoNotaFiscalRepository.findById(produtoNotaFiscalId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto Nota Fiscal", "id", produtoNotaFiscalId));
    }
    
    
    // Extra método usado para incluir NF
 	@PostMapping("/")
 	public @ResponseBody  ProdutoNotaFiscal createProdutoNF(@Valid @RequestBody ProdutoNotaFiscal produtoNotaFiscal) {
 		ProdutoNotaFiscal result = produtoNotaFiscalRepository.findProdutoNF( produtoNotaFiscal.getProduto().getId(), produtoNotaFiscal.getNotaFiscal().getId());
 		if (result == null) 
 			return produtoNotaFiscalRepository.save(produtoNotaFiscal);
 		return null;
 	} 
    
 	
 	// Extra método para atualizar NF  por id
 	// Não é necessário verificar se são registros difrentes por não realizar essa alteração
 	@PutMapping("/{id}")
 	public @ResponseBody ProdutoNotaFiscal updateProdutoNF(@PathVariable(value = "id") Long produtoNotaFiscalId,
 			@Valid @RequestBody ProdutoNotaFiscal produtoNotaFiscalAntiga) { 
 		ProdutoNotaFiscal produtoNotaFiscal = produtoNotaFiscalRepository.findById(produtoNotaFiscalId)
 				.orElseThrow(() -> new ResourceNotFoundException("Produto Nota Fiscal", "id", produtoNotaFiscalId));
 		produtoNotaFiscal.setQuantidade(produtoNotaFiscalAntiga.getQuantidade());
 		produtoNotaFiscal.setValorTotal(produtoNotaFiscalAntiga.getValorTotal());
 		produtoNotaFiscal.setValorUnitario(produtoNotaFiscalAntiga.getValorUnitario());
 		return produtoNotaFiscalRepository.save(produtoNotaFiscal);
 	}

 

}
