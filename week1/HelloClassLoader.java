package main.java.jvm;


import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shizeyu
 * @date 2021/8/8-9:45
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

       new HelloClassLoader().findClass("Hello").newInstance();

    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //读取Hello.xclass
        File file1 = new File("E:\\idea-workspace\\workspace_idea3\\classLoader\\src\\main\\java\\jvm\\Hello.xlass");
        try {
            FileInputStream inputStream = new FileInputStream(file1);
            int len = inputStream.available();
            //读取为byte
            byte[] readBytes = new byte[len];
            inputStream.read(readBytes);
            for (int i = 0; i < readBytes.length; i++) {
                readBytes[i] = (byte) (255 - readBytes[i]);
            }
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
