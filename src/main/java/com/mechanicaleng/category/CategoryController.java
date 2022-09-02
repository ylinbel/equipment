package com.mechanicaleng.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.addCategory(categoryDto);
        return ResponseEntity.ok("success");
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDto categoryDto) {
        Boolean result = categoryService.updateCategory(categoryDto);
        if (result) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryEntity(@PathVariable Long id) {
        categoryService.deleteWithId(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/find-by-layers/{parentLayer}/{layer}")
    public ResponseEntity<List<CategoryDto>> findByLayers(@PathVariable(value = "parentLayer") ParentLayerEnum parentLayerEnum, @PathVariable(value = "layer") String layer1) {
        List<CategoryDto> categories = categoryService.getCategoryByParentLayerAndLayer1(parentLayerEnum, layer1);
        return categories != null ? ResponseEntity.ok(categories) : ResponseEntity.notFound().build();
    }

    @GetMapping("/find-all-subcategory/{parentLayer}")
    public ResponseEntity<List<String>> findAllSubcategory(@PathVariable(value = "parentLayer") ParentLayerEnum parentLayerEnum) {
        List<String> list = categoryService.findAllSubCategory(parentLayerEnum);
        return ResponseEntity.ok(list);
    }
}
