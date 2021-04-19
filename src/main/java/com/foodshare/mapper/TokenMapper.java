package com.foodshare.mapper;

import com.foodshare.entity.Token;
import com.foodshare.entity.TokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {
    long countByExample(TokenExample example);

    int deleteByExample(TokenExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(Token record);

    int insertSelective(Token record);

    List<Token> selectByExampleWithRowbounds(TokenExample example, RowBounds rowBounds);

    List<Token> selectByExample(TokenExample example);

    Token selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByExample(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);

    List<Token> selectCondition(@Param("example") TokenExample example, @Param("fields") String fields);
}