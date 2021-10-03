package com.inetum.services;

import com.inetum.entity.Category;
import com.inetum.entity.Product;
import com.inetum.repository.CategoryRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.Silent.class)
class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;

    private final Category categoryUpdate = new Category();

    private final Category categoryNotUpdated = new Category();

    private AutoCloseable autoCloseable;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getallCategory() {
//        // when
//        categoryService.getallCategory();
//        //then
//        verify(categoryRepository).findAll();
        List<Category> list = new ArrayList<>();

        Category cat1 = new Category();

        cat1.setId(1);
        cat1.setName("Informatique");

        Category cat2 = new Category();


        cat1.setId(2);
        cat1.setName("éléctroménager");

        list.add(cat1);
        list.add(cat2);
        given(categoryRepository.findAll()).willReturn(list);

        List<Category> expected = categoryService.getallCategory();
        assertEquals(expected,list);
    }


    @Test
    void saveCategory() {

        //when
        Category category = new Category();
        categoryService.saveCategory(category);

        //then
        verify(categoryRepository).save(category);
        Assert.notNull(category);
    }


    @Test
    void removeCategory() {
        //when
        long idCat;
        idCat = 1;
        categoryService.removeCategory(idCat);

        //then
        verify(categoryRepository).deleteById(idCat);
    }

    @Test
    void updateCategoryTest() {
        Category category = new Category();
        category.setId(1);
        category.setName("beauty");
        categoryService.saveCategory(category);
        Category cat2Change = new Category();
        cat2Change.setName("beautify");
        when(categoryRepository.getById(1L)).thenReturn(cat2Change);
        categoryService.updateCategory(1L,cat2Change);
        //verify(categoryRepository).save(new Category());
        //verify((categoryRepository)).getById(category.getId());
        assertThat(categoryRepository.getById(1L).getName()).isEqualTo("beautify");
    }

    @Test
    void findOneCategory() {
        long idCat = 1;
        Category category = new Category();
        category.setName("voiture");
        categoryService.saveCategory(category);
        when(categoryRepository.getById(idCat)).thenReturn(category);
        assertThat(categoryRepository.getById(idCat).getName()).isEqualTo("voiture");

    }

    @Test
    void getOneProductInformation() {

        Category category = new Category();
        category.setId(1);
        category.setName("beauty");
        Product p1 = new Product();
        p1.setName("111");
        List<Product> products = new ArrayList<Product>();
        products.add(p1);
        category.setProducts(products);
        categoryRepository.save(category);
        Optional<Category> optionalCategory = categoryRepository.findById(1l);
        assertEquals(categoryService.getOneProductInformation(1l),optionalCategory);
//        Category category1 = categoryRepository.getById(1L);
//        assertThat(category1).isEqualTo(category);
    }
}