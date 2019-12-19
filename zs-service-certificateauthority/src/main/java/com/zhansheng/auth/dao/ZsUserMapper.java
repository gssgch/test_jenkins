package com.zhansheng.auth.dao;


import com.zhansheng.framework.domain.UserManager.TbUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ZsUserMapper {


    //根据账号查询用户信息
   public TbUser findByUsername(String username);





}
