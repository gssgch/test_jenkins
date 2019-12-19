package com.zhansheng.management_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * HDFS配置类
 * @author xufu
 */
@Configuration
public class HdfsConfig {
	@Value("${hdfs.path}")
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}