package com.foodshare.mapper;

import com.foodshare.entity.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMapper {
    int deleteByPrimaryKey(String md5);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(String md5);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}