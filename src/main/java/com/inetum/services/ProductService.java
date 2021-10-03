package com.inetum.services;

import com.inetum.entity.Category;
import com.inetum.entity.Product;
import com.inetum.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public void saveProduct(Product product, long id){
        Category category = this.categoryService.findOneCategory(id);
        product.setCategory(category);
        List<Product> products = category.getProducts();
        products.add(product);
        category.setProducts(products);
        this.categoryService.saveCategory(category);
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public List<Product> findAllProductOfOneCategory(long id){
        Category category = this.categoryService.findOneCategory(id);
        return category.getProducts();
    }

    public void UpdateExistingProduct(long id, Product product){
        Product product1 = this.productRepository.getById(id);
        product1.setName(product.getName());
        product1.setQuantity(product.getQuantity());
        this.productRepository.save(product1);
    }

    public void deleteProduct(long id){
        this.productRepository.deleteById(id);
    }

    public Optional<Product> getSpecificProduct(Long id){
        return  this.productRepository.findById(id);
    }
}


