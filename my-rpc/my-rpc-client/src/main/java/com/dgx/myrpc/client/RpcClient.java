package com.dgx.myrpc.client;

import com.dgx.codec.Decoder;
import com.dgx.codec.Encoder;
import com.dgx.myrpc.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @author dgx
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());
        this.selector.init(this.config.getServers(), this.config.getConnectCount(), this.config.getTransportClass());
    }
    public <T> T getProxy(Class<T> clazz){
        return (T)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz},
                new RemoteInvoker(clazz, encoder,decoder,selector));
    }
}
