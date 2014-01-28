package cn.iaa.util.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import cn.iaa.core.log.LogUtil;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class NettyServer implements Runnable {

	@Inject
	private NettyConfig nettyConfig;

	@Inject
	@Named("tcpPort")
	private InetSocketAddress tcpPort;

	@Inject
	private StringProtocolInitializer stringProtocolInitializer;

	private ServerBootstrap serverBootstrap;
	private Channel serverChannel;
	private NioEventLoopGroup bossGroup;
	private NioEventLoopGroup workerGroup;

	@SuppressWarnings("unchecked")
	public ServerBootstrap bootstrap() {
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(stringProtocolInitializer);
		Map<ChannelOption<?>, Object> tcpChannelOptions = nettyConfig.tcpChannelOptions();
		Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
		for (@SuppressWarnings("rawtypes")
		ChannelOption option : keySet) {
			b.option(option, tcpChannelOptions.get(option));
		}
		return b;
	}

	@PostConstruct
	public void init() throws Exception {
		bossGroup = nettyConfig.bossGroup();
		workerGroup = nettyConfig.workerGroup();
		serverBootstrap = bootstrap();
		serverChannel = serverBootstrap.bind(tcpPort).sync().channel().closeFuture().sync().channel();
	}

	@PreDestroy
	public void stop() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		serverChannel.close();
	}

	@Override
	public void run() {
		try {
			LogUtil.info(getClass(), "Begin Netty Server Initial!");
			init();
			LogUtil.info(getClass(), "End Netty Server Initial!");
		} catch (Exception e) {
			LogUtil.error(getClass(), "Netty Server Run Error!", e);
		} finally {
			stop();
		}
	}

}
