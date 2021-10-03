package com.inetum.services;

import com.inetum.entity.Category;
import com.inetum.repository.CategoryRepository;
import com.inetum.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category){
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //category.setMade_date(timestamp);
       // this.categoryRepository.save(category);
       return this.categoryRepository.save(category);
    }

    public List<Category> getallCategory(){
        return  this.categoryRepository.findAll();
    }

    public void removeCategory (long id){
        this.categoryRepository.deleteById(id);
    }

    public void updateCategory(long id, Category category){
        Category cat = this.categoryRepository.getById(id);
        cat.setName(category.getName());
        this.categoryRepository.save(cat);
    }

    public Category findOneCategory (Long id){
        return this.categoryRepository.getById(id);
    }

    public Optional<Category> getOneProductInformation(Long id){
        return this.categoryRepository.findById(id);
    }
}
