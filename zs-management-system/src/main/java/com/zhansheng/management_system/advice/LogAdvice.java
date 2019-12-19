package com.zhansheng.management_system.advice;


import com.zhansheng.framework.domain.UserManager.TbLog;
import com.zhansheng.management_system.annotation.LogAnnotation;
import com.zhansheng.management_system.service.TbLogsServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 统一日志处理
 * @author
 * @description
 * @date 2019/5/16
 */
@Aspect
@Component
public class LogAdvice {

	@Autowired
	private TbLogsServiceImpl logsSercice;

	@Around(value = "@annotation(com.zhansheng.management_system.annotation.LogAnnotation)")
	public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {
		TbLog sysLogs = new TbLog();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		String module = null;
		LogAnnotation logAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
		module = logAnnotation.module();
		if (StringUtils.isEmpty(module)) {
			ApiOperation apiOperation = methodSignature.getMethod().getDeclaredAnnotation(ApiOperation.class);
			if (apiOperation != null) {
				module = apiOperation.value();
			}
		}

		if (StringUtils.isEmpty(module)) {
			throw new RuntimeException("没有指定日志module");
		}
		sysLogs.setFModule(module);

		try {
			Object object = joinPoint.proceed();

			sysLogs.setFFlag("1");
			logsSercice.save(sysLogs);

			return object;
		} catch (Exception e) {
			sysLogs.setFFlag("0");
			sysLogs.setFRemark(e.getMessage());
			logsSercice.save(sysLogs);
			throw e;
		}

	}
}
