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
import br.com.escriba.model.Produto;
import br.com.escriba.util.Util;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class ProdutoControllerTest {

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
        	      .get("/produtos/listAll")
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isOk())
        	      .andExpect(jsonPath("$[0].id", is(1)))
        	      .andExpect(jsonPath("$[0].nome", is("Produto Um")))
        	      .andExpect(jsonPath("$[1].id", is(2)))
        	      .andExpect(jsonPath("$[1].nome", is("Produto Dois")))
        	      ;
	    		
    }
	
	@Test
	public void createProduto() throws Exception {
		Produto produto = new Produto();
		long x = 3;
		produto.setId(x);
		produto.setNome("NOME TESTE");
		produto.setValorUnitario(123);
		produto.setGrupoProduto(new GrupoProduto (1, "Grupo Um"));
		String jsonString = Util.asJsonString(produto);
		mockMvc.perform( MockMvcRequestBuilders
		.post("/produtos/").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonString))
		.andExpect(status().isOk())
		.andExpect(jsonPath("nome", is("NOME TESTE")))
		.andExpect(jsonPath("valorUnitario", is(123.0)));

	}
	
	@Test
	public void getProduto() throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
				.get("/produtos/1")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is(1)))
		.andExpect(jsonPath("nome", is("Produto Um")))
		.andExpect(jsonPath("valorUnitario", is(10.0)));
		
		 // caso pesquise por id inexistente retorna not found
        
		 mockMvc.perform( MockMvcRequestBuilders
	      	      .get("/produtos/100")
	      	      .accept(MediaType.APPLICATION_JSON))
	      	      .andDo(print())
	      	      .andExpect(status().isNotFound())
	      	      ;
	}
	
	@Test
	public void updateProduto() throws Exception {
		Produto produto = new Produto();
		long x = 3;
		produto.setId(x);
		produto.setNome("NOME TESTE ATUALIZADO");
		produto.setValorUnitario(123.0);
		produto.setGrupoProduto(new GrupoProduto (1, "Grupo Um"));
		String jsonString = Util.asJsonString(produto);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/produtos/3").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonString))
		.andDo(print())
		.andExpect(jsonPath("id", is(3)))
		.andExpect(jsonPath("nome", is("NOME TESTE ATUALIZADO")));
		
		// Caso id inexistente
		x = 100;
		Produto produtoNovo = new Produto();
		produtoNovo.setId(x);
		produtoNovo.setNome("NOME TESTE ATUALIZADO");
		produtoNovo.setValorUnitario(123.0);
		produtoNovo.setGrupoProduto(new GrupoProduto (1, "Grupo Um"));
		String jsonStringNova = Util.asJsonString(produtoNovo);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/produtos/100").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonStringNova))
		.andDo(print())
		 .andExpect(status().isNotFound())
		;
	}
}
