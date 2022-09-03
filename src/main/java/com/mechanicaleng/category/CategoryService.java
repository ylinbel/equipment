package com.mechanicaleng.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<CategoryDto> getCategoryByParentLayerAndLayer1(ParentLayerEnum parentLayerEnum, String layer1) {
        List<CategoryEntity> entities = categoryRepository.findCategoryEntitiesByParentLayerEnumAndLayer1Like(parentLayerEnum, layer1);
        return getCategoryDtos(entities);
    }

    public List<CategoryDto> getCategoryDtos(List<CategoryEntity> entities) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        entities.forEach(category -> {
            categoryDtoList.add(category.toDto());
        });
        return categoryDtoList;
    }

    public List<String> findAllSubCategory(ParentLayerEnum layer) {
        List<String> result = new ArrayList<>();
        List<String> list = categoryRepository.findAllLayer1(layer);
        for(String element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
}
