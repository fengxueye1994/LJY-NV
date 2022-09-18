package com.lsljy.config.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * 数据库连接池Druid初始化配置类
 * 配置这个类才能项目启动的时候初始化数据库连接池并且交给spring管理
 */
@Configuration
public class DruidConfig {

    // http://127.0.0.1:8081/druid/index.html

    @Value("${druid.login.username}")
    private String userName;

    @Value("${druid.login.password}")
    private String password;

    @Value("${druid.initial-size}")//初始化连接数量
    private Integer initialSize;

    @Value("${druid.min-idle}")//最小链接数量
    private Integer minIdle;

    @Value("${druid.max-active}")//最大链接数量
    private Integer maxActive;

    @Value("${druid.max-wait}")//配置获取连接等待超时的时间
    private Integer maxWait;


    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", userName);// 用户名
        initParameters.put("loginPassword", password);// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpn,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    //配置数据库的基本连接信息
    @Bean(name="default_datadatasource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")//在application.properties中读取配置信息注入到DruidDataSource里
    public DataSource dataSource(){
        DruidDataSource druidDataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        //初始化配置
        druidDataSource.setInitialSize(initialSize);//初始化物理连接的数量
        druidDataSource.setMinIdle(minIdle);//
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        try {
            druidDataSource.addFilters("stat,wall");//stat是sql监控，wall是防火墙(如果不添加则监控无效)，不能添加log4j不然会出错
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

//    @Bean(name="default_datadatasource")
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource druidDataSource(){
//        return new DruidDataSource();
//    }


}

