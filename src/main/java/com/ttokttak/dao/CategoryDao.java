package com.ttokttak.dao;

import com.ttokttak.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {
    // 전체 카테고리 조회
    List<Category> findAll();

    // 타입별 조회 (income | expense)
    List<Category> findByType(@Param("type") String type);
}
