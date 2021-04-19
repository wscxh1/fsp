package com.foodshare.entity;

import lombok.Data;

@Data
public class GroupPermission {
    private Byte groupId;
    private Integer permissionId;
    private String permissionUrl;
}
