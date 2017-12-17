package com.shortener.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.model.ShortUrl;
import com.shortener.repository.ShortUrlRepository;
import com.shortener.util.AliasGenerator;
import com.shortener.vo.ErrorVO;
import com.shortener.vo.ResultVO;
import com.shortener.vo.ShortenUrlVO;
import com.shortener.vo.StatisticsVO;

@RestController
public class ShortenUrlController {
	private static final int INITIAL_ALIAS_LENGTH = 3;

	@Autowired
	ShortUrlRepository shortUrlRepository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultVO shortenUrl(@RequestParam(value="url", required=false) String url,
							   @RequestParam(value="alias", required=false) String alias,
							   HttpServletResponse response ) {
		
		long startTime = System.currentTimeMillis();
		
		/* Tratando esse caso explicitamente em vez de usar required=true no parâmetro
		   para poder retornar um erro explicando o problema. */
		if (url == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return ErrorVO.NO_URL_GIVEN;
		}
		
		if (alias == null) {
			alias = generateNewAlias();
		} else if (shortUrlRepository.exists(alias)) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return ErrorVO.ALIAS_ALREADY_EXISTS;
		}
		
		ShortUrl shortUrl = new ShortUrl(alias, url);
		shortUrlRepository.save(shortUrl);
		
		long endTime = System.currentTimeMillis();
		StatisticsVO statistics = new StatisticsVO(endTime - startTime);
		
		return new ShortenUrlVO(alias, url, statistics);
		
	}
	
	private String generateNewAlias() {
		int length = INITIAL_ALIAS_LENGTH;
		String newAlias;
		
		do {
			newAlias = AliasGenerator.generate(length);
			length++;
		} while (shortUrlRepository.exists(newAlias));
		
		return newAlias;
	}
}
