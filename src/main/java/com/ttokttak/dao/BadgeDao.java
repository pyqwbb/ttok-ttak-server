package com.ttokttak.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BadgeDao {
    List<Map<String, Object>> findMonthlyTopCategory(@Param("uid") String uid);
}
