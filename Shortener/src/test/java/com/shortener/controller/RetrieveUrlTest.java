package com.shortener.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.shortener.model.ShortUrl;
import com.shortener.repository.ShortUrlRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RetrieveUrlTest {
	@Autowired
	private ShortUrlRepository shortUrlRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		ShortUrl url = new ShortUrl();
		url.setAlias("bemobi");
		url.setUrl("http://www.bemobi.com.br");
		shortUrlRepository.save(url);
	}
	
	// Pesquisa por um alias existente e recebe um json com a URL.
	@Test
	public void getByAlias_ShouldReturnCorrectUrl() throws Exception {
		this.mockMvc.perform(get("/bemobi"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.url", is("http://www.bemobi.com.br")));
	}
	
	// Pesquisa por um alias inexistente e espera um erro.
	@Test
	public void getByAlias_NonexistentAlias_ShouldReturnError() throws Exception {
		this.mockMvc.perform(get("/WRONG_ALIAS"))
					.andExpect(status().isNotFound())
					.andExpect(jsonPath("$.err_code", is("002")))
					.andExpect(jsonPath("$.description", is("SHORTENED URL NOT FOUND")));
	}
	
	// Pesquisa por um alias com case errado e espera um erro. (Teste de regressão)
	@Test
	public void getByAlias_CaseInsensitive_ShouldReturnError() throws Exception {
		this.mockMvc.perform(get("/BEMOBI"))
					.andExpect(status().isNotFound())
					.andExpect(jsonPath("$.err_code", is("002")))
					.andExpect(jsonPath("$.description", is("SHORTENED URL NOT FOUND")));
	}
	
	// Pesquisa por um alias existente e recebe um json com a URL.
	@Test
	public void viewCount_ShouldIncrementAfterRetrieval() throws Exception {
		this.mockMvc.perform(get("/bemobi"))
					.andExpect(status().isOk());
		
		ShortUrl url = shortUrlRepository.findOne("bemobi");
		assertEquals(1, url.getViews());
	}
}
