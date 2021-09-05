package io.github.shizeyu;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shizeyu
 * @date 2021/9/6-1:16
 */
@Component
@Data
@ConfigurationProperties(prefix = "user")
public class UserExample {
    private String userName="shizeyu";

    private String className="05-2021";

    private String schoolName="极客";
}
