package com.foodshare.mapper;

import com.foodshare.entity.UserPermission;
import com.foodshare.entity.UserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionMapper {
    long countByExample(UserPermissionExample example);

    int deleteByExample(UserPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    List<UserPermission> selectByExampleWithRowbounds(UserPermissionExample example, RowBounds rowBounds);

    List<UserPermission> selectByExample(UserPermissionExample example);

    UserPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    int updateByExample(@Param("record") UserPermission record, @Param("example") UserPermissionExample example);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);

    List<UserPermission> selectCondition(@Param("example") UserPermissionExample example, @Param("fields") String fields);
}