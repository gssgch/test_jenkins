package com.zhansheng.management_system.config;


import com.zhansheng.framework.exception.ExceptionCatch;
import com.zhansheng.framework.model.response.CommonCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**用户管理自定义的异常类，其中定义异常类型所对应的错误代码
 * @author Administrator
 * @version 1.0
 **/
@ControllerAdvice//控制器增强
public class CustomExceptionCatch extends ExceptionCatch {

    static {
        builder.put(AccessDeniedException.class, CommonCode.UNAUTHORISE);
    }

}
