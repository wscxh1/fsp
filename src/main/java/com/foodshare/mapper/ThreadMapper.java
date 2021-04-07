package com.foodshare.mapper;

import com.foodshare.entity.Thread;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Thread record);

    int insertSelective(Thread record);

    Thread selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Thread record);

    int updateByPrimaryKey(Thread record);
}