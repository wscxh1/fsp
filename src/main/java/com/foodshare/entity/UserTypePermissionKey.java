package com.foodshare.entity;

import java.io.Serializable;

public class UserTypePermissionKey implements Serializable {
    private Byte userTypeId;

    private Integer permissionId;

    public Byte getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Byte userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}