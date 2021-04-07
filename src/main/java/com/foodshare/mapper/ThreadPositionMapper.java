package com.foodshare.mapper;

import com.foodshare.entity.ThreadPosition;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadPositionMapper {
    int deleteByPrimaryKey(Long threadId);

    int insert(ThreadPosition record);

    int insertSelective(ThreadPosition record);

    ThreadPosition selectByPrimaryKey(Long threadId);

    int updateByPrimaryKeySelective(ThreadPosition record);

    int updateByPrimaryKey(ThreadPosition record);
}