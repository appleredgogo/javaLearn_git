package io.github.shizeyu;

import io.github.shizeyu.bean.Klass;
import io.github.shizeyu.bean.School;
import io.github.shizeyu.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shizeyu
 * @date 2021/9/6-1:13
 */
@Configuration
@EnableConfigurationProperties(UserExample.class)
public class UserAutoConfigure {
    @Autowired
    private UserExample userExample;

    @Bean
    public School setSchool(){
        School school = new School();
        school.setSchoolName(userExample.getSchoolName());
        return school;
    }
    @Bean
    public Klass setKlass(){
        Klass klass = new Klass();
        klass.setClassName(userExample.getClassName());
        return klass;
    }
    @Bean
    public Student setStudent(){
        Student student = new Student();
        student.setUserName(userExample.getUserName());
        return student;
    }

}
