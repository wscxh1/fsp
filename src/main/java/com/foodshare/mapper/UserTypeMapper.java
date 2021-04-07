package com.foodshare.mapper;

import com.foodshare.entity.UserType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(UserType record);

    int insertSelective(UserType record);

    UserType selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(UserType record);

    int updateByPrimaryKey(UserType record);
}