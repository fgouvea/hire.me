package com.shortener.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;

import com.shortener.model.ShortUrl;
import com.shortener.repository.ShortUrlRepository;
import com.shortener.vo.ShortenUrlVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ShortenUrlTest {
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
	
	// Encurta uma url com um alias válido.
	@Test
	public void shortenUrlWithAlias_ShouldReturnOkAndPersistData() throws Exception {
		this.mockMvc.perform(post("/create?url=http://www.google.com&alias=google"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.alias", is("google")))
					.andExpect(jsonPath("$.url", is("http://www.google.com")))
					.andExpect(jsonPath("$.statistics", is(notNullValue())))
					.andExpect(jsonPath("$.statistics.time_taken", is(notNullValue())));
		
		assertTrue(shortUrlRepository.exists("google"));
	}
	
	// Encurta uma url com um alias já usado e espera um erro.
	@Test
	public void shortenUrlWithAlias_ExistingAlias_ShouldReturnConflictAndError() throws Exception {
		this.mockMvc.perform(post("/create?url=http://www.google.com&alias=bemobi"))
					.andExpect(status().isConflict())
					.andExpect(jsonPath("$.err_code", is("001")))
					.andExpect(jsonPath("$.description", is("CUSTOM ALIAS ALREADY EXISTS")));;
	}
	
	// Tentar encurtar sem passar a url e espera um erro.
	@Test
	public void shortenUrlWithAlias_NoUrl_ShouldReturnBadRequestAndError() throws Exception {
		this.mockMvc.perform(post("/create?alias=noUrl"))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$.err_code", is("003")))
					.andExpect(jsonPath("$.description", is("NO URL GIVEN")));;
	}
	
	// Tentar encurtar sem passar a url e espera um erro.
	@Test
	public void shortenUrlWithoutAlias_ShouldReturnAliasAndPersistData() throws Exception {
		MvcResult result = this.mockMvc.perform(post("/create?url=http://www.youtube.com"))
									   .andExpect(status().isOk())
									   .andExpect(jsonPath("$.alias", is(notNullValue())))
									   .andExpect(jsonPath("$.statistics", is(notNullValue())))
									   .andExpect(jsonPath("$.statistics.time_taken", is(notNullValue())))
									   .andReturn();
		
		String json = result.getResponse().getContentAsString();

		ObjectMapper objectMapper = new ObjectMapper();
		ShortenUrlVO resultVO = objectMapper.readValue(json, ShortenUrlVO.class);
		
		String alias = resultVO.getAlias();
		
		assertTrue(shortUrlRepository.exists(alias));
		
		ShortUrl savedUrl = shortUrlRepository.findOne(alias);
		assertEquals("http://www.youtube.com", savedUrl.getUrl());
	}
}
