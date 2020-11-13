package com.dgx.myrpc.client;

import com.dgx.myrpc.Peer;
import com.dgx.myrpc.transport.TransportClient;

import java.util.List;

/**
 * 路由选择
 * 表示选择连接哪个server
 * @author DGX
 */
public interface TransportSelector {
    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立了多少个连接
     * @param clazz client的实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);
    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);
    void close();

}
