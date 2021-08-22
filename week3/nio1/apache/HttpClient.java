package java0.nio2.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient
 * @author shizeyu
 * @date 2021/8/15-19:31
 */
public class HttpClient {
    public static void main(String[] args) throws IOException {

        getHttpClient("http://localhost:8801");
    }

    public static String getHttpClient(String remoteCallUrl){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //声明get请求
        HttpGet httpGet = new HttpGet(remoteCallUrl);
        //发送请求
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = httpClient.execute(httpGet);
            //判断状态码
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity entity = response.getEntity();
                //使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
                String string = EntityUtils.toString(entity, "utf-8");
                System.out.println(string);
                result = string;
            }
        }catch (IOException e){
            System.out.println("连接失败！！"+e.getMessage());
            result = "连接失败！！";
        }finally {
            try {
                //5.关闭资源
                response.close();
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return result;
    }
}
