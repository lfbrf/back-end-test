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
import br.com.escriba.util.Util;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class GrupoProdutoControllerTest {

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
				.get("/grupoProdutos/listAll")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].nome", is("Grupo Um")))
		.andExpect(jsonPath("$[1].id", is(2)))
		.andExpect(jsonPath("$[1].nome", is("Grupo Dois")))
		;

	}

	@Test
	public void getFindLike() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/grupoProdutos/findLike/Quatr")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", is(4)))
		.andExpect(jsonPath("$[0].nome", is("Grupo Quatro")));

	}

	@Test
	public void findMatchLetter() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/grupoProdutos/findMatchLetter/e")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is(16)))
		.andExpect(jsonPath("nome", is("Grupo Dezesseis")));

	}

	@Test
	public void paginacao() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/grupoProdutos/listAll/?inicio=0&quantidade=16")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[15].id", is(16)))
		.andExpect(jsonPath("$[15].nome", is("Grupo Dezesseis")));

	}

	@Test
	public void createGrupoProduto() throws Exception {
		GrupoProduto grupo = new GrupoProduto();
		long x = 17;
		grupo.setId(x);
		grupo.setNome("NOME TESTE");
		String jsonString = Util.asJsonString(grupo);
		mockMvc.perform( MockMvcRequestBuilders
		.post("/grupoProdutos/").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonString))
		.andExpect(status().isOk())
		.andExpect(jsonPath("nome", is("NOME TESTE")));

	}
	
	@Test
	public void getGrupoProduto() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/grupoProdutos/1")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is(1)))
		.andExpect(jsonPath("nome", is("Grupo Um")));
		
		 // caso pesquise por id inexistente retorna not found
        mockMvc.perform( MockMvcRequestBuilders
      	      .get("/grupoProdutos/100")
      	      .accept(MediaType.APPLICATION_JSON))
      	      .andDo(print())
      	      .andExpect(status().isNotFound())
      	      ;

	}
	
	@Test
	public void updateGrupoProduto() throws Exception {
		GrupoProduto grupo = new GrupoProduto();
		long x = 10;
		grupo.setId(x);
		grupo.setNome("NOME TESTE ATUALIZADO");
		String jsonString = Util.asJsonString(grupo);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/grupoProdutos/10").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonString))
		.andDo(print())
		.andExpect(jsonPath("id", is(10)))
		.andExpect(jsonPath("nome", is("NOME TESTE ATUALIZADO")));
		
		// Caso id inexistente
		x = 100;
		GrupoProduto grupoNovo = new GrupoProduto();
		grupoNovo.setId(x);
		grupoNovo.setNome("NOME TESTE ATUALIZADO");
		String jsonStringNova = Util.asJsonString(grupoNovo);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/grupoProdutos/100").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonStringNova))
		.andDo(print())
		 .andExpect(status().isNotFound())
		;
	}
	
	
}
