package com.lsljy.config;

import lombok.Data;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * 设置配置文件globalConfigKo.yml转为bean管理
 * 在ConfigKo类中映射
 */
@Configuration
@Data
//@Scope("prototype")
//@PropertySource(value = "classpath:globalConfigKo.yml")
public class ConfigKoBean {
    // 加载YML格式自定义配置文件
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//		yaml.setResources(new FileSystemResource(ResourceUtils.CLASSPATH_URL_PREFIX + "permission.yml"));//File引入
        yaml.setResources(new ClassPathResource("config/globalConfigKo.yml"));//class引入，避免了路径处理问题
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

}



