package com.order.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.order.model.ProductEntity;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping
	public ResponseEntity<String> getproductList() {
		String uri="http://localhost:8081/product/";
		ResponseEntity<String> response=restTemplate.getForEntity(uri, String.class);
		return response;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getproductByOrderId(@PathVariable("id")long id) {
		String uri="http://localhost:8081/product/"+id;
		ResponseEntity<String> response=restTemplate.getForEntity(uri, String.class);
		return response;
		
	}
	
	@PostMapping("/createOrderByproduct")
	public String createOrderByProduct(@RequestBody ProductEntity product) {
		String uri="http://localhost:8081/product/createProduct";
		HttpHeaders headers=new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ProductEntity> model=new HttpEntity<>(product,headers);
		return restTemplate.exchange(uri, HttpMethod.POST, model, String.class).getBody();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	
	
	
}
