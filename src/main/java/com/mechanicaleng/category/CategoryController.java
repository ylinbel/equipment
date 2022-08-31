package com.mechanicaleng.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
