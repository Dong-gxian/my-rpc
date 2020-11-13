package com.dgx.myrpc.client;

import com.dgx.codec.Decoder;
import com.dgx.codec.Encoder;
import com.dgx.codec.JSONDecoder;
import com.dgx.codec.JSONEncoder;
import com.dgx.myrpc.Peer;
import com.dgx.myrpc.transport.HTTPTransportClient;
import com.dgx.myrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author dgx
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    /**
     * 默认每个server的连接数
     */
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
