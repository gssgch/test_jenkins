package com.zhansheng.management_system.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhansheng.api.cmsuser.TbPermissionInterface;
import com.zhansheng.framework.domain.UserManager.TbPermission;
import com.zhansheng.management_system.dao.TbPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/5/28
 */

@Service
@Transactional
public class TbPermissionServiceImpl implements TbPermissionInterface{

    @Autowired
    private TbPermissionMapper permissionMapper;

    /**
     * 菜单树
     * 获取一级菜单二级菜单三级菜单
     * @param pId
     * @param permissionsAll
     * @param array
     */
    private void setPermissionsTree(Integer pId, List<TbPermission> permissionsAll, JSONArray array) {
        for (TbPermission per : permissionsAll) {
            if (per.getFParentId().equals(pId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getFParentId().equals(per.getFPermissionId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(per.getFPermissionId(), permissionsAll, child);
                }
            }
        }
    }

    @Override
    public JSONArray permissionsAll() {
        List<TbPermission> permissions = permissionMapper.listParents();
        JSONArray array = new JSONArray();
        setPermissionsTree(0, permissions, array);
        return array;
    }

}
