package com.ttokttak.controller;

import com.ttokttak.domain.Category;
import com.ttokttak.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 전체 카테고리 조회
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    // 수입 카테고리 조회
    @GetMapping("/income")
    public ResponseEntity<List<Category>> getIncomeCategories() {
        return ResponseEntity.ok(categoryService.getByType("income"));
    }

    // 지출 카테고리 조회
    @GetMapping("/expense")
    public ResponseEntity<List<Category>> getExpenseCategories() {
        return ResponseEntity.ok(categoryService.getByType("expense"));
    }
}
