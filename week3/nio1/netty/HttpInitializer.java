package java0.nio2.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {
	
	@Override
	public void initChannel(SocketChannel ch) {
		//网络处理中中间这一段，需要我们自己控制，它的流水线、流程的这部分
		ChannelPipeline p = ch.pipeline();
		//HttpServer编码器
		p.addLast(new HttpServerCodec());
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		//添加了自己定义的HttpHandler
		p.addLast(new HttpHandler());
	}
}
