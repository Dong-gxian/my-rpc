package com.dgx.myrpc.client;

import com.dgx.myrpc.Peer;
import com.dgx.myrpc.transport.TransportClient;
import com.dgx.myrpc.utils.ReflectionUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dgx
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {
    /**
     * 存储所有已经连接好的TransportClient
     * client 连接池
     */
    private List<TransportClient> clients;
    public RandomTransportSelector(){
        clients = new ArrayList<>();
    }
    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peers) { //对每个server都建立count个连接
            for (int i = 0; i < count; ++i) { //整个连接池总共有peers.size()*count个连接，每个client对应一条连接
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server:{}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() { //随机选择一个连接
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) { //加入回连接池
        clients.add(client);
    }

    @Override
    public synchronized void close() { //关闭所有的client（连接）
        for(TransportClient client:clients){
            client.close();
        }
        clients.clear();//List的clear()函数
    }
}
