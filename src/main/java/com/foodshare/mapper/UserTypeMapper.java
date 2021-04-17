package com.foodshare.mapper;

import com.foodshare.entity.UserType;
import com.foodshare.entity.UserTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserTypeMapper {
    long countByExample(UserTypeExample example);

    int deleteByExample(UserTypeExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(UserType record);

    int insertSelective(UserType record);

    List<UserType> selectByExampleWithRowbounds(UserTypeExample example, RowBounds rowBounds);

    List<UserType> selectByExample(UserTypeExample example);

    UserType selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") UserType record, @Param("example") UserTypeExample example);

    int updateByExample(@Param("record") UserType record, @Param("example") UserTypeExample example);

    int updateByPrimaryKeySelective(UserType record);

    int updateByPrimaryKey(UserType record);

    List<UserType> selectCondition(@Param("example") UserTypeExample example, @Param("fields") String fields);
}