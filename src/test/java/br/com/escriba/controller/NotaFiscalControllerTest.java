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
import br.com.escriba.model.NotaFiscal;
import br.com.escriba.util.Util;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class NotaFiscalControllerTest {

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
        	      .get("/notasFiscais/listAll")
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isOk())
        	      .andExpect(jsonPath("$[0].id", is(1)))
        	      .andExpect(jsonPath("$[0].valorTotal", is(20.0)))
        	      .andExpect(jsonPath("$[0].numero", is("1234")))
        	      ;
	    		
    }
	
	@Test
	public void getNotaFiscalPorId() throws Exception {
		// cenário ideal
        mockMvc.perform( MockMvcRequestBuilders
        	      .get("/notasFiscais/1")
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isOk())
        	      .andExpect(jsonPath("id", is(1)))
        	      .andExpect(jsonPath("valorTotal", is(20.00)))
        	      .andExpect(jsonPath("numero", is("1234")))
        	      ;
        // caso pesquise por id inexistente retorna not found
        mockMvc.perform( MockMvcRequestBuilders
      	      .get("/notasFiscais/10")
      	      .accept(MediaType.APPLICATION_JSON))
      	      .andDo(print())
      	      .andExpect(status().isNotFound())
      	      ;
	    		
    }
	
	@Test
	public void getNotaFiscalPorNumero() throws Exception {
		// cenário ideal
        mockMvc.perform( MockMvcRequestBuilders
        	      .get("/notasFiscais?numero=1234")
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isOk())
        	      .andExpect(jsonPath("id", is(1)))
        	      .andExpect(jsonPath("valorTotal", is(20.00)))
        	      .andExpect(jsonPath("numero", is("1234")))
        	      ;
        // caso pesquise por número inexistente retorna not found
        mockMvc.perform( MockMvcRequestBuilders
        	      .get("/notasFiscais?numero=456789")
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isOk())
        	      ;
	    		
    }
	
	@Test
	public void createNotaFiscal() throws Exception {
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setId((long) 1);
		notaFiscal.setNumero("99998");
		notaFiscal.setValorTotal(4568.0);
		String jsonString = Util.asJsonString(notaFiscal);
		mockMvc.perform( MockMvcRequestBuilders
		.post("/notasFiscais/").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonString))
		.andExpect(status().isOk())
		.andExpect(jsonPath("numero", is("99998")))
		.andExpect(jsonPath("valorTotal", is(4568.0)));

	}
	
	@Test
	public void updateNotaFiscal() throws Exception {
		NotaFiscal notaFiscal = new NotaFiscal();
		long x = 2;
		notaFiscal.setId(x);
		notaFiscal.setNumero("661");
		notaFiscal.setValorTotal(525.0);
		String jsonString = Util.asJsonString(notaFiscal);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/notasFiscais/2").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonString))
		.andDo(print())
		.andExpect(jsonPath("id", is(2)))
		.andExpect(jsonPath("numero", is("661")))
		.andExpect(jsonPath("valorTotal", is(525.0)));
		
		// Caso id inexistente
		x = 100;
		NotaFiscal notaFiscalNova = new NotaFiscal();
		notaFiscalNova.setId(x);
		notaFiscalNova.setNumero("456");
		notaFiscalNova.setValorTotal(789);
		String jsonStringNova = Util.asJsonString(notaFiscalNova);
		mockMvc.perform( MockMvcRequestBuilders
		.put("/notasFiscais/100").contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(jsonStringNova))
		.andDo(print())
		 .andExpect(status().isNotFound())
		;
	}
	
	
}
