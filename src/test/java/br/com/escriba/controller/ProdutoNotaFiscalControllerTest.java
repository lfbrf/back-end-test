package br.com.escriba.controller;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.escriba.App;
import br.com.escriba.model.GrupoProduto;
import br.com.escriba.model.NotaFiscal;
import br.com.escriba.model.Produto;
import br.com.escriba.model.ProdutoNotaFiscal;
import br.com.escriba.util.Util;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class ProdutoNotaFiscalControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void listAll() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/produtosNotaFiscal/listAll")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[2].valorUnitario", is(1548.0)))
		.andExpect(jsonPath("$[2].quantidade", is(4)))
		.andExpect(jsonPath("$[2].valorTotal", is(90544.0)))
		;

	}

	@Test
	public void getNotaFiscalPorId() throws Exception {
		// cenário ideal
		mockMvc.perform( MockMvcRequestBuilders
				.get("/produtosNotaFiscal/1")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is(1)))
		.andExpect(jsonPath("valorUnitario", is(10.00)))
		.andExpect(jsonPath("valorTotal", is(20.00)))
		.andExpect(jsonPath("quantidade", is(2)))
		;
		// caso pesquise por id inexistente retorna not found
		mockMvc.perform( MockMvcRequestBuilders
				.get("/produtosNotaFiscal/100")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isNotFound())
		;
	}


	@Test
	public void createProdutoNF() throws Exception {
		ProdutoNotaFiscal produtoNotaFiscal = new ProdutoNotaFiscal();
		produtoNotaFiscal.setId((long) 3);
		produtoNotaFiscal.setQuantidade(2);
		produtoNotaFiscal.setValorTotal(250.0);
		produtoNotaFiscal.setValorUnitario(32.0);
		produtoNotaFiscal.setNotaFiscal(new NotaFiscal(3, 25.0, "3598"));
		produtoNotaFiscal.setProduto(new Produto(2, 10.0, "Produto", new GrupoProduto(1, "Grupo Um") ));
		
		String jsonString = Util.asJsonString(produtoNotaFiscal);
		mockMvc.perform( MockMvcRequestBuilders
				.post("/produtosNotaFiscal/").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonString))
		.andExpect(jsonPath("quantidade", is(2)))
		.andExpect(jsonPath("valorTotal", is(250.0)))
		.andExpect(jsonPath("valorUnitario", is(32.0)));
		
		// Teste caso inclua mesma nota fiscal e produto de um registro já adicionado
		mockMvc.perform( MockMvcRequestBuilders
				.post("/produtosNotaFiscal/").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(jsonString))
		.andExpect(jsonPath("quantidade").doesNotExist())
		.andExpect(jsonPath("valorTotal").doesNotExist())
		.andExpect(jsonPath("valorUnitario").doesNotExist());
	}
	
	@Test
	public void updateProdutoNF() throws Exception {
		ProdutoNotaFiscal produtoNotaFiscal = new ProdutoNotaFiscal();
		produtoNotaFiscal.setId((long) 1);
		produtoNotaFiscal.setQuantidade(6);
		produtoNotaFiscal.setValorTotal(187.0);
		produtoNotaFiscal.setValorUnitario(90.0);
		produtoNotaFiscal.setNotaFiscal(new NotaFiscal(1, 25.0, "3598"));
		produtoNotaFiscal.setProduto(new Produto(1, 10.0, "Produto", new GrupoProduto(1, "Grupo Um") ));
		String jsonString = Util.asJsonString(produtoNotaFiscal);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/produtosNotaFiscal/1").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonString))
		.andDo(print())
		.andExpect(jsonPath("id", is(1)))
		.andExpect(jsonPath("quantidade", is(6)))
		.andExpect(jsonPath("valorTotal", is(187.0)))
		.andExpect(jsonPath("valorUnitario", is(90.0)));
		
		// Caso id inexistente
		ProdutoNotaFiscal produtoNotaFiscalNova = new ProdutoNotaFiscal();
		produtoNotaFiscalNova.setQuantidade(6);
		produtoNotaFiscalNova.setValorTotal(187.0);
		produtoNotaFiscalNova.setValorUnitario(90.0);
		produtoNotaFiscalNova.setNotaFiscal(new NotaFiscal(1, 25.0, "3598"));
		produtoNotaFiscalNova.setProduto(new Produto(1, 10.0, "Produto", new GrupoProduto(1, "Grupo Um") ));
		String jsonStringNova = Util.asJsonString(produtoNotaFiscalNova);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/notasFiscais/100").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonStringNova))
		.andDo(print())
		 .andExpect(status().isNotFound())
		;
	}
	


}
