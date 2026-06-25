package com.ttokttak.dao;

import com.ttokttak.domain.Budget;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BudgetDao {
    // 거래 목록 조회
    List<Budget> findAll(Map<String, Object> params);

    // 거래 단건 조회
    Budget findById(@Param("id") String id);

    // 거래 등록
    int insert(Budget budget);

    // 거래 수정
    int update(Budget budget);

    // 거래 삭제
    int delete(@Param("id") String id);
}
