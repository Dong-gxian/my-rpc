package com.dgx.myrpc.server;

import com.dgx.myrpc.Request;
import com.dgx.myrpc.utils.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) throws InvocationTargetException, IllegalAccessException {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
