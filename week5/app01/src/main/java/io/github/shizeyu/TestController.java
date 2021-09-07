package io.github.shizeyu;

import io.github.shizeyu.bean.School;
import io.github.shizeyu.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shizeyu
 * @date 2021/9/7-23:08
 */
@RestController
public class TestController {
    @Autowired
    private UserAutoConfigure userAutoConfigure;

    @GetMapping(value = "test")
    public String test() {
        Student user = userAutoConfigure.setStudent();
        return user.getUserName();
    }
}
