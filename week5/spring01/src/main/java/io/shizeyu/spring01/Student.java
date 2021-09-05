package io.shizeyu.spring01;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Student{
    private String name="shizeyu";
}
