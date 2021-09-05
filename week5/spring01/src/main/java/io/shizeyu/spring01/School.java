package io.shizeyu.spring01;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Data
public class School {

    private String schoolName= "jk";

    @Bean
    public Klass klass(){
        return new Klass();
    }

    /*@Bean
    public Student student(){
        return  new Student();
    }*/
    
}
