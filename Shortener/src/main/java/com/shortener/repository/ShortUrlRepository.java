package com.shortener.repository;

import org.springframework.data.repository.CrudRepository;
import com.shortener.model.ShortUrl;

public interface ShortUrlRepository extends CrudRepository<ShortUrl, String> {
	
}
