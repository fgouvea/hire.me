package com.shortener.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.shortener.model.ShortUrl;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, String> {
	
	List<ShortUrl> findTop10ByOrderByViewsDesc();
}
