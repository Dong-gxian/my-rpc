package com.dgx.myrpc.server;

import com.dgx.codec.Decoder;
import com.dgx.codec.Encoder;
import com.dgx.codec.JSONDecoder;
import com.dgx.codec.JSONEncoder;
import com.dgx.myrpc.transport.HTTPTransportServer;
import com.dgx.myrpc.transport.TransportServer;
import lombok.Data;

/**
 * server configuration：依赖协议（proto）、网络传输（transport）模块、公用（commons）模块和commons-io
 * server配置：哪个网络模块来传输数据，哪个序列化的实现，server的端口
 * 在这里是静态指定好的
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> TransportClass = HTTPTransportServer.class;//为何要写成这样？
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
