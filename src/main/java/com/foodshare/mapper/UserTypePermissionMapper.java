package com.foodshare.mapper;

import com.foodshare.entity.UserTypePermissionExample;
import com.foodshare.entity.UserTypePermissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypePermissionMapper {
    long countByExample(UserTypePermissionExample example);

    int deleteByExample(UserTypePermissionExample example);

    int deleteByPrimaryKey(UserTypePermissionKey key);

    int insert(UserTypePermissionKey record);

    int insertSelective(UserTypePermissionKey record);

    List<UserTypePermissionKey> selectByExampleWithRowbounds(UserTypePermissionExample example, RowBounds rowBounds);

    List<UserTypePermissionKey> selectByExample(UserTypePermissionExample example);

    int updateByExampleSelective(@Param("record") UserTypePermissionKey record, @Param("example") UserTypePermissionExample example);

    int updateByExample(@Param("record") UserTypePermissionKey record, @Param("example") UserTypePermissionExample example);

    List<UserTypePermissionKey> selectCondition(@Param("example") UserTypePermissionExample example, @Param("fields") String fields);
}