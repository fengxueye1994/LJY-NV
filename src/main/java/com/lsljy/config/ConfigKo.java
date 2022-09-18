package com.lsljy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

/**配置文件的映射模型
 *
 *
 *
 */

@Data
@Component//默认是单例的
@ConfigurationProperties(prefix = "uri")//映射uri下的文件内容
public class ConfigKo {
    // 这里不需要 @Value

    private Map<String ,String> dataMap;//等于配置文件的key名字
    //配置文件的键值对存放在dataMap里，谁使用的话注入这个类就可以取得里面的所有东西
}


