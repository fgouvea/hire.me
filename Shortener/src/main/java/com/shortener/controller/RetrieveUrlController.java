package com.shortener.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.model.ShortUrl;
import com.shortener.repository.ShortUrlRepository;
import com.shortener.vo.ErrorVO;
import com.shortener.vo.ResultVO;
import com.shortener.vo.RetrieveUrlVO;

@RestController
public class RetrieveUrlController {
	@Autowired
	ShortUrlRepository shortUrlRepository;
	
	@RequestMapping(value = "/{alias}", method = RequestMethod.GET)
	public ResultVO retrieveUrl(@PathVariable(value="alias") final String alias,
								HttpServletResponse response) {
		
		ShortUrl url = shortUrlRepository.findOne(alias);
		
		if (url == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return ErrorVO.URL_NOT_FOUND;
		} else {
			url.incrementViews();
			shortUrlRepository.save(url);
			
			return new RetrieveUrlVO(url.getUrl());
		}
	}
}
