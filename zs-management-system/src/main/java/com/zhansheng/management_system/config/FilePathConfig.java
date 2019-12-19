package com.zhansheng.management_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
  * 设置虚拟路径，访问绝对路径下资源
  * @author Administrator
  *
  */
@Configuration
public class FilePathConfig implements WebMvcConfigurer{
	@Value("${files.staticAccessPath}")
	private String staticAccessPath;
	@Value("${files.rootPath}")
	private String rootPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(staticAccessPath).addResourceLocations("file:///" + rootPath);
	}

}