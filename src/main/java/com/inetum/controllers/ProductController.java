package com.inetum.controllers;

import com.inetum.DTO.ProductDto;
import com.inetum.entity.Category;
import com.inetum.entity.Product;
import com.inetum.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
 private final ProductService productService;

 private final ModelMapper modelMapper;

  @Autowired
  public ProductController(ProductService productService, ModelMapper modelMapper) {
    this.productService = productService;
      this.modelMapper = modelMapper;
  }

  //this method allow to consumers to get the list of all product
  @GetMapping(path = "/findAll")
  public List<Product> findAllProduct(){
    return this.productService.getProducts();
  }

  ////this method allow to consumers to add a new product in a specific category of product
  @PostMapping(path = "/addNewProduct/{id}")
  public void addNewProduct(@RequestBody ProductDto productDto, @PathVariable long id){
      Product product = modelMapper.map(productDto,Product.class);
      this.productService.saveProduct(product,id);
  }

  //this method allow to consumers to retrieve the list of product of one category
  @GetMapping(path = "/getProduct/{id}")
  public List<Product> getOneCategoryProduct(@PathVariable long id){
      return this.productService.findAllProductOfOneCategory(id);
  }

  //this method allow to consumers to update a product
  @PutMapping(path = "/update/{id}")
  public void updateProduct (@PathVariable long id, @RequestBody ProductDto productDto){
      Product product = modelMapper.map(productDto,Product.class);
     this.productService.UpdateExistingProduct(id,product);
  }

  //this method allow to consumers to remove product from database
  @DeleteMapping(path = "/deleteProduct/{id}")
  public void removeProduct(@PathVariable long id){
      this.productService.deleteProduct(id);
  }

  @GetMapping(path = "/getSpecificProduct/{id}")
  public Optional<Product> getSpecificProduct(@PathVariable Long id){
      return  this.productService.getSpecificProduct(id);
  }
}
