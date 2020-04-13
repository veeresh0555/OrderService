package com.order.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.order.model.ProductEntity;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	Environment env;
	
	@GetMapping("/info")
	public String info() {
		String port=env.getProperty("local.server.port");
		return "call Server "+port;
	}
	
	
	@GetMapping
	public ResponseEntity<List<ProductEntity>> getAllProducts(){
		System.out.println("In ProductService:-> Controller ");
		List<ProductEntity> productList=new ArrayList<ProductEntity>();
		ProductEntity products=new ProductEntity();
		products.setId(123);
		products.setPname("mobile");
		productList.add(products);
		products=new ProductEntity();
		products.setId(124);
		products.setPname("Laptop");
		productList.add(products);
		productList.stream().forEach(prdlst->System.out.println("ProductId: "+prdlst.getId()+"\t ProductName: "+prdlst.getPname()));
		return new ResponseEntity<List<ProductEntity>>(productList,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ProductEntity>> getProductById(@PathVariable("id")Long id){
		System.out.println("Product Controller: FindProductById: "+id);
		List<ProductEntity> productList=new ArrayList<ProductEntity>();
		ProductEntity products=new ProductEntity();
		products.setId(125);
		products.setPname("prod1");
		productList.add(products);
		products=new ProductEntity();
		products.setId(126);
		products.setPname("prod2");
		productList.add(products);
		productList.stream().forEach(prdlst->System.out.println("ProductId: "+prdlst.getId()+"\t ProductName: "+prdlst.getPname()));
		return new ResponseEntity<List<ProductEntity>>(productList,new HttpHeaders(),HttpStatus.OK);
	}
	@GetMapping("/prodreqById")
	public ResponseEntity<List<ProductEntity>> getProdByreqId(@RequestParam("id") long id){
		System.out.println("Product Controller: FindProductById: "+id);
		List<ProductEntity> productList=new ArrayList<ProductEntity>();
		ProductEntity products=new ProductEntity();
		products.setId(126);
		products.setPname("prodreq-1");
		productList.add(products);
		products=new ProductEntity();
		products.setId(127);
		products.setPname("prodreq-2");
		productList.add(products);
		productList.stream().forEach(prdlst->System.out.println("ProductId: "+prdlst.getId()+"\t ProductName: "+prdlst.getPname()));
		return new ResponseEntity<List<ProductEntity>>(productList,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@PostMapping("/createproduct")
	public ResponseEntity<ProductEntity> saveProduct(@RequestBody ProductEntity prodEntity){
	
		System.out.println("Enter Product Controller....>");
		
		System.out.println("ProductID: "+prodEntity.getId()+"\t ProdName"+prodEntity.getPname());
		
		return new ResponseEntity<ProductEntity>(prodEntity,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	
}
