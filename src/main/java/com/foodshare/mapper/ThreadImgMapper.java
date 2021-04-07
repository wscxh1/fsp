package com.foodshare.mapper;

import com.foodshare.entity.ThreadImgKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadImgMapper {
    int deleteByPrimaryKey(ThreadImgKey key);

    int insert(ThreadImgKey record);

    int insertSelective(ThreadImgKey record);
}