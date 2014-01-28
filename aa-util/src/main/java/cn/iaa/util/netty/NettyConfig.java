package cn.iaa.util.netty;

import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class NettyConfig {

	@Inject
	@Named("keepAlive")
	private boolean keepAlive;

	@Inject
	@Named("backlog")
	private int backlog;

	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup();
	}

	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup();
	}

	public Map<ChannelOption<?>, Object> tcpChannelOptions() {
		Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
		options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
		options.put(ChannelOption.SO_BACKLOG, backlog);
		return options;
	}

}
