package com.dgx.myrpc.client;

import com.dgx.codec.Decoder;
import com.dgx.codec.Encoder;
import com.dgx.myrpc.Request;
import com.dgx.myrpc.Response;
import com.dgx.myrpc.ServiceDescriptor;
import com.dgx.myrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dgx
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response resp = invokeRemote(request);
        if(resp == null || resp.getCode()!=0){
            throw new IllegalStateException("fail to invoke remote: "+resp);
        }
        return resp.getData();
    }

    /**
     * 发送请求，获取远程调用返回的输入流，构造返回相应并返回
     * @param request 要发送的请求
     * @return 根据远程调用返回的输入流构造的响应
     */
    private Response invokeRemote(Request request) {
        Response resp = null;
        TransportClient client = null;
        try{
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(revice, revice.available());
            resp = decoder.decode(inBytes, Response.class);
        }catch (IOException e){
            log.warn(e.getMessage(), e);
            resp.setCode(1);
            resp.setMessage("RpcClient got error: "+ e.getClass() + ":" + e.getMessage());
        }finally {
            selector.release(client);
        }
        return resp;
    }
}
