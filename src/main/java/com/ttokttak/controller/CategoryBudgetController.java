package com.ttokttak.controller;

import com.ttokttak.domain.CategoryBudget;
import com.ttokttak.dto.CategoryBudgetRequest;
import com.ttokttak.service.CategoryBudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category-budget")
@RequiredArgsConstructor
public class CategoryBudgetController {

    private final CategoryBudgetService categoryBudgetService;

    // 임시 고정 uid — JWT 구현 후 교체 예정
    private static final String TEMP_UID = "a63ec1eb";

    // GET /api/category-budget
    @GetMapping
    public ResponseEntity<List<CategoryBudget>> getAll() {
        return ResponseEntity.ok(categoryBudgetService.getAll(TEMP_UID));
    }

    // POST /api/category-budget
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryBudgetRequest req) {
        categoryBudgetService.create(TEMP_UID, req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "예산이 등록됐어요."));
    }

    // PUT /api/category-budget/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody CategoryBudgetRequest req
    ) {
        categoryBudgetService.update(id, req);
        return ResponseEntity.ok(Map.of("message", "예산이 수정됐어요."));
    }

    // DELETE /api/category-budget/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        categoryBudgetService.delete(id);
        return ResponseEntity.ok(Map.of("message", "예산이 삭제됐어요."));
    }
}
