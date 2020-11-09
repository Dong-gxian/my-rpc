package com.dgx.myrpc.server;

import com.dgx.codec.Decoder;
import com.dgx.codec.Encoder;
import com.dgx.myrpc.transport.HTTPTransportServer;
import com.dgx.myrpc.transport.RequestHandler;
import com.dgx.myrpc.transport.TransportServer;
import com.dgx.myrpc.utils.ReflectionUtils;
import com.sun.net.httpserver.HttpsServer;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
@Slf4j
public class RPCServer {
    private RpcServerConfig config;
    private TransportServer net;//接口类型
    private Encoder encoder;//接口类型
    private Decoder decoder;//接口类型
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RPCServer(RpcServerConfig config) {
        this.config = config;
        //this.net = new HTTPTransportServer();
        try{
            this.net = ReflectionUtils.newInstance(config.getTransportClass());
            this.net.init(config.getPort(), this.handler);//网络接收到请求之后应该交给this.handler来处理
            this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
            this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass, bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() { //内部类
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            //读到数据
            byte[] inBytes =
        }
    }
}
