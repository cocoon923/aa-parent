package cn.iaa.util.spring.moduleloader;

import io.netty.channel.ChannelHandler;
import cn.iaa.util.netty.NettyModule;
import cn.iaa.util.netty.NettyServer;
import cn.iaa.util.spring.netty.NettyAbility;

import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class NettyModuleLoader extends ModuleLoader implements NettyAbility {

	private int port;
	private ChannelHandler channelHandler;

	/**
	 * Constructor of {@link NettyModuleLoader}.
	 * 
	 * @param configLocation
	 *            config location of spring xml.
	 * @param port
	 *            listen port
	 * @param channelHandler
	 *            channel handler
	 * @throws Exception
	 */
	public NettyModuleLoader(String configLocation, int port, ChannelHandler channelHandler) throws Exception {
		super(configLocation);
		this.port = port;
		this.channelHandler = channelHandler;
		this.loadNettyService();
	}

	@Override
	public void loadNettyService() throws Exception {
		Injector inject = Guice.createInjector(new NettyModule(port, channelHandler));
		(new Thread(inject.getInstance(NettyServer.class))).start();
	}
}
