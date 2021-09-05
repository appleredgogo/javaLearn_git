package io.shizeyu.spring01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shizeyu
 * @date 2021/9/5-22:18
 */
public class PrintBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationcontext.xml");
      /*  for(String s:applicationContext.getBeanDefinitionNames()){
             System.out.println(s);
        }*/
        Student st = new Student();
        System.out.println(st.getName());
        School school01 = (School)applicationContext.getBean(School.class);
        Klass klass01 = applicationContext.getBean(Klass.class);
        Student student01 = applicationContext.getBean(Student.class);
        try {

            System.out.println(school01.getSchoolName());
            System.out.println(klass01.getClassName());
           System.out.println(student01.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
