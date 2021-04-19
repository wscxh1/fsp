package com.foodshare.mapper;

import com.foodshare.entity.GroupPermission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GroupPermissionMapper {
    @Select("SELECT t.id group_id,p.id permission_id,p.url permission_url FROM user_type AS t " +
            "JOIN user_type_permission AS tp ON t.id=tp.user_type_id " +
            "LEFT JOIN user_permission AS p ON tp.permission_id = p.id;")
    List<GroupPermission> getAll();
}
