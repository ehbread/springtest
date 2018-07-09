package com.example.test.search.repository;

import java.net.URI;

import com.example.test.common.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SearchBookRestTemplate {

	@Value("${appkey.kakao}")
	private String appKey;

	@Autowired
	private RestTemplateFactory restTemplateFactory;

	private HttpEntity<?> createHttpEntity(MultiValueMap<String, Object> paramMap) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "KakaoAK " + appKey);

		return new HttpEntity<>(paramMap, requestHeaders);
	}

	public <T> ResponseEntity<T> getSearch(MultiValueMap<String, Object> paramMap, Class<T> clazz) {

		try {

			return restTemplateFactory.getRestTemplate().exchange(getUrl(), HttpMethod.POST, createHttpEntity(paramMap), clazz);
		} catch (Exception e) {
			log.error("통신실패", e);
		}
		return null;
	}

	private URI getUrl() {

		UriComponents uriCompornent = UriComponentsBuilder.newInstance().scheme("https").host("dapi.kakao.com").path("/v2/search/book").build();

		return uriCompornent.toUri();

	}
}