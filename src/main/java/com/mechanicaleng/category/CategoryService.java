package com.mechanicaleng.category;

import com.mechanicaleng.location.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;


    public void addCategory(CategoryDto categoryDto) {
        CategoryEntity category = CategoryEntity.fromDto(categoryDto);
        categoryRepository.save(category);
    }

    public void deleteWithId(long id) {
        categoryRepository.deleteById(id);
    }

    public Boolean updateCategory(CategoryDto categoryDto) {
        Optional<CategoryEntity> opCategoryEntity = categoryRepository.findById(categoryDto.getId());
        if (opCategoryEntity.isEmpty()) return false;
        CategoryEntity categoryEntity = opCategoryEntity.get();
        categoryEntity.updateFromDto(categoryDto);
        categoryRepository.save(categoryEntity);
        return true;
    }
}
