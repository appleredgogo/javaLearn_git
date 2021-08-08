package main.java.jvm;


import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shizeyu
 * @date 2021/8/8-9:45
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<?> hello = new HelloClassLoader().loadClass("Hello");
        //加载hello实用类
        Object instance = hello.newInstance();
        //获取方法
        Method me = hello.getMethod("hello");
        //反射调用
        me.invoke(instance);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //读取Hello.xclass
        File file1 = new File("E:\\idea-workspace\\workspace_idea3\\classLoader\\src\\main\\java\\jvm\\Hello.xlass");
        File file2 = new File("E:\\idea-workspace\\workspace_idea3\\classLoader\\src\\main\\java\\tool\\Hello.class");
        try {
            FileInputStream inputStream = new FileInputStream(file1);
            int len = inputStream.available();
            //读取为byte
            byte[] readBytes = new byte[len];

            inputStream.read(readBytes);
            for (int i = 0; i < readBytes.length; i++) {
                readBytes[i] = (byte) (255 - readBytes[i]);
            }
            FileOutputStream outputStream = new FileOutputStream(file2);
            outputStream.write(readBytes);//写入
            outputStream.close();//关闭
            inputStream.close();
            return defineClass(name, readBytes, 0, readBytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
