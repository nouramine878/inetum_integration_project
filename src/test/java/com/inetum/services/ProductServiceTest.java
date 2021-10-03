package com.inetum.services;

import com.inetum.entity.Category;
import com.inetum.entity.Product;
import com.inetum.repository.CategoryRepository;
import com.inetum.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductService productService;

    @Mock
    CategoryService categoryService;

    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        ProductService productService = new ProductService(productRepository,
                categoryService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveProduct() {
        List<Product> products = new ArrayList<Product>();
        Category category = new Category();
        category.setId(1);
        category.setName("category name");
        Product product = new Product();
        product.setName("prod name");
        products.add(product);
        categoryRepository.save(category);
        verify(categoryRepository).save(category);
        when(categoryRepository.getById(1L)).thenReturn(category);
        category.setProducts(products);
        categoryRepository.save(category);
        assertThat(categoryRepository.getById(1L).getProducts()).isEqualTo(products);
        assertEquals(1, category.getProducts().size());


    }

    @Test
    void getProducts() {
        productRepository.findAll();
        verify(productRepository).findAll();
    }

    @Test
    void findAllProductOfOneCategory() {
        List<Product> products = new ArrayList<Product>();
        List<Product> productss = new ArrayList<Product>();
        Category category = new Category();
        category.setId(1L);
        category.setName("cat name");

        Product product = new Product();
        product.setId(1L);
        product.setName("prod name");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("prod name");

        products.add(product);
        products.add(product1);

        category.setProducts(products);
        categoryService.saveCategory(category);
        when(categoryService.findOneCategory(1L)).thenReturn(category);

        //List<Product> expected = categoryService.findOneCategory(1L).getProducts();
        List<Product> expected = productService.findAllProductOfOneCategory(1L);

        assertEquals(2, expected.size());


    }

    @Test
    void updateExistingProduct() {

        Product product = new Product();
        product.setId(1);
        product.setName("prod name");
        productRepository.save(product);

        Product newProd = new Product();
        newProd.setName("new Prod Name");

        when(productRepository.getById(1L)).thenReturn(newProd);
        productService.UpdateExistingProduct(1L, newProd);

        assertEquals("new Prod Name", productRepository.getById(1L).getName());
    }

    @Test
    void deleteProduct() {

        long idProd = 1;
        productService.deleteProduct(1);

        verify(productRepository).deleteById(idProd);
    }

    @Test
    void getSpecificProduct() {

        long idProd = 1;
        productService.getSpecificProduct(idProd);

        verify(productRepository).findById(idProd);
    }
}