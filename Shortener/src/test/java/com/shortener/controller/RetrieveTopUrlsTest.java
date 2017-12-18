package com.shortener.controller;

import static org.hamcrest.Matchers.*;
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
public class RetrieveTopUrlsTest {
	@Autowired
	private ShortUrlRepository shortUrlRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		ShortUrl url = new ShortUrl("top2", "http://www.bemobi.com.br");
		url.setViews(17);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top1", "http://www.google.com");
		url.setViews(52);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top11", "http://www.plus.google.com");
		url.setViews(0);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top3", "http://www.reddit.com");
		url.setViews(15);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top7", "http://www.facebook.com");
		url.setViews(6);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top9", "http://www.explosm.net");
		url.setViews(3);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top8", "http://www.globoesporte.globo.com");
		url.setViews(5);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top6", "http://www.reddit.com/r/childrenFallingOver");
		url.setViews(10);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top5", "http://www.youtube.com");
		url.setViews(11);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top10", "https://spring.io/");
		url.setViews(2);
		shortUrlRepository.save(url);
		
		url = new ShortUrl("top4", "https://github.com/");
		url.setViews(12);
		shortUrlRepository.save(url);
	}
	
	// Requisita a lista de top 10 por views e espera receber a lista correta.
	@Test
	public void getTop10_ShouldReceiveListInCorrectOrder() throws Exception {
		this.mockMvc.perform(get("/top10Urls"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(10)))
					.andExpect(jsonPath("$[0].url", is("http://www.google.com")))
					.andExpect(jsonPath("$[0].views", is(52)))
					.andExpect(jsonPath("$[1].url", is("http://www.bemobi.com.br")))
					.andExpect(jsonPath("$[2].url", is("http://www.reddit.com")))
					.andExpect(jsonPath("$[3].url", is("https://github.com/")))
					.andExpect(jsonPath("$[4].url", is("http://www.youtube.com")))
					.andExpect(jsonPath("$[5].url", is("http://www.reddit.com/r/childrenFallingOver")))
					.andExpect(jsonPath("$[6].url", is("http://www.facebook.com")))
					.andExpect(jsonPath("$[7].url", is("http://www.globoesporte.globo.com")))
					.andExpect(jsonPath("$[8].url", is("http://www.explosm.net")))
					.andExpect(jsonPath("$[9].url", is("https://spring.io/")));
	}
}
