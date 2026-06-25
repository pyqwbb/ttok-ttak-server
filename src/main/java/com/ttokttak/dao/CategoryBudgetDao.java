package com.ttokttak.dao;

import com.ttokttak.domain.CategoryBudget;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryBudgetDao {
    // 예산 목록 조회
    List<CategoryBudget> findByUid(@Param("uid") String uid);

    // 예산 등록
    int insert(CategoryBudget categoryBudget);

    // 예산 수정
    int update(CategoryBudget categoryBudget);

    // 예산 삭제
    int delete(@Param("id") String id);
}
