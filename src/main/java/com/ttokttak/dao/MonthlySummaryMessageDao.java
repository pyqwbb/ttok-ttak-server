package com.ttokttak.dao;

import com.ttokttak.domain.MonthlySummaryMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonthlySummaryMessageDao {
    List<MonthlySummaryMessage> findAll();
}
