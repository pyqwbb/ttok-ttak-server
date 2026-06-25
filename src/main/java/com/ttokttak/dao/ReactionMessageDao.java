package com.ttokttak.dao;

import com.ttokttak.domain.ReactionMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReactionMessageDao {
    List<ReactionMessage> findAll();
}
