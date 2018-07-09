package com.example.test.common;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestTemplateFactory {

	@Getter
	private RestTemplate restTemplate;

	@Value("${resttemplate.api.server.readTimeOut}")
	private int connectionTimeOut;
	@Value("${resttemplate.api.server.connectTimeout}")
	private int readTimeOut;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	private void setRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout((int) TimeUnit.MILLISECONDS.convert(connectionTimeOut, TimeUnit.MILLISECONDS));
		httpRequestFactory.setReadTimeout((int) TimeUnit.MILLISECONDS.convert(readTimeOut, TimeUnit.MILLISECONDS));
		restTemplate = new RestTemplate(httpRequestFactory);

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
				jsonConverter.setObjectMapper(objectMapper);
			}
		}

	}

}
