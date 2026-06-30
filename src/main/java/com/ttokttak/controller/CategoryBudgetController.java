package com.ttokttak.controller;

import com.ttokttak.domain.CategoryBudget;
import com.ttokttak.dto.CategoryBudgetRequest;
import com.ttokttak.service.CategoryBudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category-budget")
@RequiredArgsConstructor
public class CategoryBudgetController {

    private final CategoryBudgetService categoryBudgetService;

    // GET /api/category-budget
    @GetMapping
    public ResponseEntity<List<CategoryBudget>> getAll(HttpServletRequest request) {
        String uid = (String) request.getAttribute("uid");
        return ResponseEntity.ok(categoryBudgetService.getAll(uid));
    }

    // POST /api/category-budget
    @PostMapping
    public ResponseEntity<?> create(
            HttpServletRequest request,
            @RequestBody CategoryBudgetRequest req
    ) {
        String uid = (String) request.getAttribute("uid");
        categoryBudgetService.create(uid, req);
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
