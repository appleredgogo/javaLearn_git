package java0.nio2.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import java0.nio2.apache.HttpClient;
import java0.nio2.apache.OkHttp;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

//整个NettyServer启动以后，客户端的请求进来,读取客户端请求的这个Handler
public class HttpHandler extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        ctx.flush();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;//转为http request对象，可以拿到它的内部的结构
            String uri = fullRequest.uri();
            String remoteCallUrl = "http://localhost:8801";
            String body = HttpClient.getHttpClient(remoteCallUrl);
            //String body = OkHttp.getOkHttp(remoteCallUrl);
            handlerTest(fullRequest, ctx, body);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
	//在这里可以直接给客户端发送HTTP相关的报文，与之前BIO的方式类似
    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String body) {
	   FullHttpResponse response = null;
        try {
            String value = body; // 对接上次作业的httpclient或者okhttp请求另一个url的响应数据

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            System.out.println("处理出错:"+e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
                ctx.flush();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
