package com.ttokttak.controller;

import com.ttokttak.domain.Budget;
import com.ttokttak.dto.BudgetRequest;
import com.ttokttak.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    // GET /api/budget
    // GET /api/budget?type=expense&cid=1&startDate=2026-06-01&endDate=2026-06-30
    @GetMapping
    public ResponseEntity<List<Budget>> getAll(
            HttpServletRequest request,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer cid,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        String uid = (String) request.getAttribute("uid");

        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("type", type);
        params.put("cid", cid);
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        return ResponseEntity.ok(budgetService.getAll(params));
    }

    // GET /api/budget/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getOne(@PathVariable String id) {
        return ResponseEntity.ok(budgetService.getById(id));
    }

    // POST /api/budget
    @PostMapping
    public ResponseEntity<?> create(
            HttpServletRequest request,
            @RequestBody BudgetRequest req
    ) {
        String uid = (String) request.getAttribute("uid");
        budgetService.create(uid, req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "거래가 등록됐어요."));
    }

    // PUT /api/budget/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody BudgetRequest req
    ) {
        budgetService.update(id, req);
        return ResponseEntity.ok(Map.of("message", "거래가 수정됐어요."));
    }

    // DELETE /api/budget/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        budgetService.delete(id);
        return ResponseEntity.ok(Map.of("message", "거래가 삭제됐어요."));
    }
}
