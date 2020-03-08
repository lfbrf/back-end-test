package br.com.escriba.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.escriba.exception.ResourceNotFoundException;
import br.com.escriba.model.NotaFiscal;
import br.com.escriba.repository.NotaFiscalRepository;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/notasFiscais")
public class NotaFiscalController {

    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    @GetMapping("/listAll")
    public @ResponseBody List<NotaFiscal> getAllGruposProdutos() {
        return notaFiscalRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody NotaFiscal getNotaFiscalPorId(@PathVariable(value = "id") Long notaFiscalID) {
        return notaFiscalRepository.findById(notaFiscalID)
                .orElseThrow(() -> new ResourceNotFoundException("Nota Fiscal", "id", notaFiscalID));
    }
    
    @GetMapping("")
    @ResponseBody
    public   NotaFiscal getNotaFiscalPorNumero(@RequestParam String numero) {
        return notaFiscalRepository.findByNumero(numero);
    } 
    
    // Extra método usado para incluir NF
 	@PostMapping("/")
 	public @ResponseBody  NotaFiscal createNotaFiscal(@Valid @RequestBody NotaFiscal notaFiscal) {
 		return notaFiscalRepository.save(notaFiscal);
 	} 
    
 	
 	// Extra método para atualizar NF  por id
 	@PutMapping("/{id}")
 	public @ResponseBody NotaFiscal updateNotaFiscal(@PathVariable(value = "id") Long notaFiscalId,
 			@Valid @RequestBody NotaFiscal notaFiscalAntiga) {
 		NotaFiscal notaFiscal = notaFiscalRepository.findById(notaFiscalId)
 				.orElseThrow(() -> new ResourceNotFoundException("Nota Fiscal", "id", notaFiscalId));

 		notaFiscal.setNumero(notaFiscalAntiga.getNumero());
 		notaFiscal.setValorTotal(notaFiscalAntiga.getValorTotal());
 		

 		return notaFiscalRepository.save(notaFiscal);
 	}

}
