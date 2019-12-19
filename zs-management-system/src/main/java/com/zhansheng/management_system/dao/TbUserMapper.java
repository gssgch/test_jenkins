package com.zhansheng.management_system.dao;


import com.zhansheng.framework.domain.UserManager.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TbUserMapper {

    //查所有用户
    public List<TbUser> findList();

    //查询用户信息(包含分页,用户名模糊匹配)
    public List<TbUser> findByPageUsername(String username);

    //通过用户名查询用户信息
    public TbUser findOne(String username);

    //添加用户及对应的角色(账户类型)
    public int addUser(TbUser user);

    //删除用户 角色信息(删除的是中间表数据)
    public int deleteId(int userId);

    //更新user表和userandrole表数据
    public int updateUser(TbUser user);

    //通过用户名查询用户密码
    @Select("SELECT u.F_Password FROM tb_user u WHERE u.F_Username = #{username}")
    public String findUsernameByPassword(String username);

    //通过用户名修改密码
    @Update("update tb_user set F_Password = #{newPassword} WHERE F_Username = #{username}")
    public int updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

}
