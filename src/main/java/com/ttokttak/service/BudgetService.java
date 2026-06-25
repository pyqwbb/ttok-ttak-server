package com.ttokttak.service;

import com.ttokttak.domain.Budget;
import com.ttokttak.dto.BudgetRequest;

import java.util.List;
import java.util.Map;

public interface BudgetService {
    // 거래 목록 조회
    List<Budget> getAll(Map<String, Object> params);

    // 거래 단건 조회
    Budget getById(String id);

    // 거래 등록
    void create(String uid, BudgetRequest req);

    // 거래 수정
    void update(String id, BudgetRequest req);

    // 거래 삭제
    void delete(String id);
}
