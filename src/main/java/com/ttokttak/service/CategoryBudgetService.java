package com.ttokttak.service;

import com.ttokttak.domain.CategoryBudget;
import com.ttokttak.dto.CategoryBudgetRequest;

import java.util.List;

public interface CategoryBudgetService {
    // 예산 목록 조회
    List<CategoryBudget> getAll(String uid);

    // 예산 등록
    void create(String uid, CategoryBudgetRequest req);

    // 예산 수정
    void update(String id, CategoryBudgetRequest req);

    // 예산 삭제
    void delete(String id);
}
