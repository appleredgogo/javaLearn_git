package java0.nio2.apache;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * OkHttp
 * @author shizeyu
 * @date 2021/8/15-20:16
 */
public class OkHttp {
    public static void main(String[] args) throws IOException {
        getOkHttp();
    }
    public static void getOkHttp(){

        OkHttpClient okHttpClient = new OkHttpClient();
        //String url = String.format("http://%s:%s/", "localhost", "8801");
        String url ="http://localhost:8801";
        Request request = new Request.Builder().url(url).get().build();
        Call call = null;
        Response response = null;
        try {
            call = okHttpClient.newCall(request);
            response = call.execute();
            System.out.println(response.body().string());
        }catch (IOException e){
            System.out.println("连接失败！！"+e.getMessage());
        }finally {
            //5.关闭资源
            response.close();
            response.body().close();
        }

    }
}
