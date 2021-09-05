package com.example.demo.model;

import java.util.List;

/**
 * Created
 */
public interface PermissionListener {
    /**
     * 授权
     */
    void onGranted();

    /**
     * 授权拒绝
     * @param deniedPermission 权限列表
     */
    void onDenied(List<String> deniedPermission);
}
