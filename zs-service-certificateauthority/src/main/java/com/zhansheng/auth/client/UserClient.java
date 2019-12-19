package com.zhansheng.auth.client;

import com.zhansheng.framework.client.ZsServiceList;

import com.zhansheng.framework.domain.auth.Ext.ZsUserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator.
 */
@FeignClient(value = ZsServiceList.ZS_MANAGEMENT_SYSTEM)
public interface UserClient {

    //根据账号查询用户信息
    @GetMapping("/ucenter/getuserext")
    public ZsUserExt getUserext(@RequestParam("username") String username);

    /*@GetMapping(value = "/saveLogin")
    public void saveLogin(@PathVariable("userName") String userName, @PathVariable("module") String module,
                          @PathVariable("flag") String flag, @PathVariable("remark") String remark);*/


}
