package java0.nio2.apache;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * HttpClient
 * @author shizeyu
 * @date 2021/8/15-19:31
 */
public class HttpClient {
    public static void main(String[] args) throws IOException {

        //getHttpClient("http://localhost:8801");
    }

    public static String getHttpClient(FullHttpRequest inbound,String remoteCallUrl){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //声明get请求
        HttpGet httpGet = new HttpGet(remoteCallUrl);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("xjava", inbound.headers().get("xjava"));
        String result = "";
        try {
            //发送请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println("status:" + response.getStatusLine().getStatusCode());
            try {

                //判断状态码
                if(response.getStatusLine().getStatusCode()==200){
                    HttpEntity entity = response.getEntity();
                    //使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
                    String body = EntityUtils.toString(entity, "utf-8");
                    System.out.println(body);
                    result = body;
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
        }catch (IOException e){
            System.out.println("连接失败！！"+e.getMessage());
            result = "连接失败！！";
        }

        return result;
    }
}
