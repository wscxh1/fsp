package com.foodshare.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupPermission implements Serializable {
    private Byte groupId;
    private Integer permissionId;
    private String permissionUrl;
}
