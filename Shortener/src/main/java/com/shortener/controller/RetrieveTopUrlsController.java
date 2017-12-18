package com.shortener.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.model.ShortUrl;
import com.shortener.repository.ShortUrlRepository;
import com.shortener.vo.ViewCountVO;

@RestController
public class RetrieveTopUrlsController {
	@Autowired
	ShortUrlRepository shortUrlRepository;
	
	@RequestMapping(value = "/top10Urls", method = RequestMethod.GET)
	public List<ViewCountVO> retrieveUrl(HttpServletResponse response) {
		
		List<ShortUrl> topUrls = shortUrlRepository.findTop10ByOrderByViewsDesc();
		ArrayList<ViewCountVO> result = new ArrayList<ViewCountVO>(topUrls.size());
		
		for (ShortUrl url : topUrls) {
			ViewCountVO vo = new ViewCountVO(url.getAlias(), url.getUrl(), url.getViews());
			result.add(vo);
		}
		
		return result;
	}
}
