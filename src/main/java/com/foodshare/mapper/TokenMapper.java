package com.foodshare.mapper;

import com.foodshare.entity.Token;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}