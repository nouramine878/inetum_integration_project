package com.inetum.controllers;

import com.inetum.DTO.CategoryDto;
import com.inetum.entity.Category;
import com.inetum.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }
 // to start pipeline automatically without launch it manually again
    // this comment for launch both chekout and building maven project

    //we will try to build again

    //we are trying now to analyse project with sonarqube in the pipeline
    @PostMapping(path = "/add-new-category")
    public  Category addNewCategry (@RequestBody CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto,Category.class);
        return this.categoryService.saveCategory(category);
    }

    @GetMapping(path = "/get-all-category")
    public List<Category> findAllCategory(){
        return  this.categoryService.getallCategory();
    }

    @DeleteMapping(path = "/delete-category/{id}")
    public void deleteCategory(@PathVariable long id){
        this.categoryService.removeCategory(id);
    }

    @PutMapping(path = "/update/{id}")
    public void updateCategory(@PathVariable long id, @RequestBody  CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto,Category.class);
        this.categoryService.updateCategory(id,category);
    }

    @GetMapping(path = "/getOneCategory/{id}")
    public Optional<Category> getOneProduct(@PathVariable Long id){
        return this.categoryService.getOneProductInformation(id);
    }
}
