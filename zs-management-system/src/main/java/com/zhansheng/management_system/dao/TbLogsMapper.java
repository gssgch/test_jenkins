package com.zhansheng.management_system.dao;

import com.zhansheng.framework.domain.UserManager.TbLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TbLogsMapper {

    //查询所有日志信息
     List<TbLog> findPageList(@Param("userName") String userName,
                                    @Param("module") String module,
                                    @Param("flag") String flag,
                                    @Param("startTime") String startTime,
                                    @Param("endTime") String endTime);

     int save(TbLog tblog);
}
