package com.foodshare.service;

import com.foodshare.entity.UserPermission;
import com.foodshare.entity.UserType;

import java.util.List;
import java.util.Map;

public interface AuthService {
    public void refreshPermissionMap();//从db读到redis strut=> UserType->[permission1,permission2...]

    public Map<UserType, UserPermission> getPermissionMap();

    public List<UserPermission> getPermissions(Integer id);


}
