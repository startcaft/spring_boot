package com.startcaft.springboot.config;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DbConfig {
	
	private Logger logger = LoggerFactory.getLogger(DbConfig.class);
	
	@Autowired
	private DbProperties dbProperties;
	
	@Bean
	@Primary	//在多个类型为 DataSource 的 Bean中，首先使用被标注的 DataSource。Spring Boot 默认会注册一个名为 dataSource，类型为 DataSource 的 Bean。
	public DataSource druidDataSource(){
		{
			DruidDataSource dataSource = new DruidDataSource();
			dataSource.setInitialSize(dbProperties.getInitialSize());  
			dataSource.setMinIdle(dbProperties.getMinIdle());  
			dataSource.setMaxActive(dbProperties.getMaxActive());  
			dataSource.setMaxWait(dbProperties.getMaxWait());  
			dataSource.setTimeBetweenEvictionRunsMillis(dbProperties.getTimeBetweenEvictionRunsMillis());  
			dataSource.setMinEvictableIdleTimeMillis(dbProperties.getMinEvictableIdleTimeMillis());  
			dataSource.setValidationQuery(dbProperties.getValidationQuery());  
			dataSource.setTestWhileIdle(dbProperties.isTestWhileIdle());  
			dataSource.setTestOnBorrow(dbProperties.isTestOnBorrow());  
			dataSource.setTestOnReturn(dbProperties.isTestOnReturn());  
			dataSource.setPoolPreparedStatements(dbProperties.isPoolPreparedStatements());  
			dataSource.setMaxPoolPreparedStatementPerConnectionSize(dbProperties.getMaxPoolPreparedStatementPerConnectionSize());  
			try {  
				dataSource.setFilters(dbProperties.getFilters());  
			} 
			catch (SQLException e) {  
				logger.error("druid configuration initialization filter", e);  
			}  
			dataSource.setConnectionProperties(dbProperties.getConnectionProperties());  
			
			return dataSource;
		}
	}
	
	/**
	 * 内部类，基于类型安全的配置，获取到Druid连接池的配置信息。
	 * spring.data.type配置项可以忽略
	 */
	@Component
	@ConfigurationProperties(prefix="spring.datasource")
	class DbProperties{
		
		/*
		 * JDBC 配置项
		 */
		private String driverClassName;
		private String url;
		private String username;
		private String password;
		
		/*
		 * Druid 连接池配置
		 */
		private Integer initialSize;
		private Integer minIdle;
		private Integer maxActive;
		private Integer maxWait;
		private Integer timeBetweenEvictionRunsMillis;
		private Integer minEvictableIdleTimeMillis;
		private String validationQuery;
		private boolean testWhileIdle;
		private boolean testOnBorrow;
		private boolean testOnReturn;
		private boolean poolPreparedStatements;
		private Integer maxPoolPreparedStatementPerConnectionSize;
		private String filters;
		private String connectionProperties;
		
		public DbProperties() {
			super();
		}
		
		public String getDriverClassName() {
			return driverClassName;
		}
		public void setDriverClassName(String driverClassName) {
			this.driverClassName = driverClassName;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Integer getInitialSize() {
			return initialSize;
		}
		public void setInitialSize(Integer initialSize) {
			this.initialSize = initialSize;
		}
		public Integer getMinIdle() {
			return minIdle;
		}
		public void setMinIdle(Integer minIdle) {
			this.minIdle = minIdle;
		}
		public Integer getMaxActive() {
			return maxActive;
		}
		public void setMaxActive(Integer maxActive) {
			this.maxActive = maxActive;
		}
		public Integer getMaxWait() {
			return maxWait;
		}
		public void setMaxWait(Integer maxWait) {
			this.maxWait = maxWait;
		}
		public Integer getTimeBetweenEvictionRunsMillis() {
			return timeBetweenEvictionRunsMillis;
		}
		public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
			this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		}
		public Integer getMinEvictableIdleTimeMillis() {
			return minEvictableIdleTimeMillis;
		}
		public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
			this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
		}
		public String getValidationQuery() {
			return validationQuery;
		}
		public void setValidationQuery(String validationQuery) {
			this.validationQuery = validationQuery;
		}
		public boolean isTestWhileIdle() {
			return testWhileIdle;
		}
		public void setTestWhileIdle(boolean testWhileIdle) {
			this.testWhileIdle = testWhileIdle;
		}
		public boolean isTestOnBorrow() {
			return testOnBorrow;
		}
		public void setTestOnBorrow(boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}
		public boolean isTestOnReturn() {
			return testOnReturn;
		}
		public void setTestOnReturn(boolean testOnReturn) {
			this.testOnReturn = testOnReturn;
		}
		public boolean isPoolPreparedStatements() {
			return poolPreparedStatements;
		}
		public void setPoolPreparedStatements(boolean poolPreparedStatements) {
			this.poolPreparedStatements = poolPreparedStatements;
		}
		public Integer getMaxPoolPreparedStatementPerConnectionSize() {
			return maxPoolPreparedStatementPerConnectionSize;
		}
		public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
			this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
		}
		public String getFilters() {
			return filters;
		}
		public void setFilters(String filters) {
			this.filters = filters;
		}
		public String getConnectionProperties() {
			return connectionProperties;
		}
		public void setConnectionProperties(String connectionProperties) {
			this.connectionProperties = connectionProperties;
		}
	}
}
