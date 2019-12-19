package com.zhansheng.management_system.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhansheng.api.cmsuser.TbPermissionInterface;
import com.zhansheng.management_system.service.TbPermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/5/28
 */

@RestController
@RequestMapping("/Permission")
public class PermissionController implements TbPermissionInterface {

    @Autowired
    private TbPermissionServiceImpl permissionService;

    /**
     * 设置子元素
     * 2018.06.09
     *
     * @param p
     * @param permissions
     */
/*
    private void setChild(TbPermission p, List<TbPermission> permissions) {
        List<TbPermission> child = permissions.parallelStream().filter(a -> a.getFParentId().equals(p.getFPermissionId())).collect(Collectors.toList());
        p.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }
*/

    /**
     * 菜单列表
     *
     * @param pId
     * @param permissionsAll
     * @param list
     */
/*
    private void setPermissionsList(Integer pId, List<TbPermission> permissionsAll, List<TbPermission> list) {
        for (TbPermission per : permissionsAll) {
            if (per.getFParentId().equals(pId)) {
                list.add(per);
                if (permissionsAll.stream().filter(p -> p.getFParentId().equals(per.getFPermissionId())).findAny() != null) {
                    setPermissionsList(per.getFPermissionId(), permissionsAll, list);
                }
            }
        }
    }
*/

    @Override
    @PostMapping("/permissionsAll")
    public JSONArray permissionsAll() {
        return permissionService.permissionsAll();
    }

}
