package cn.iaa.util.netty;

import static com.google.inject.name.Names.named;
import io.netty.channel.ChannelHandler;

import java.net.InetSocketAddress;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class NettyModule extends AbstractModule {

	private int tcpPort;
	private ChannelHandler channelHandler;

	public NettyModule(int tcpPort, ChannelHandler channelHandler) {
		super();
		this.tcpPort = tcpPort;
		this.channelHandler = channelHandler;
	}

	@Override
	protected void configure() {
		bindConstant().annotatedWith(named("keepAlive")).to(true);
		bindConstant().annotatedWith(named("backlog")).to(100);
		bind(InetSocketAddress.class).annotatedWith(named("tcpPort")).toInstance(new InetSocketAddress(tcpPort));
	}

	@Provides
	StringProtocolInitializer provideStringProtocolInitializer() {
		StringProtocolInitializer initializer = new StringProtocolInitializer();
		initializer.setChannelHandler(channelHandler);
		return initializer;
	}

}
