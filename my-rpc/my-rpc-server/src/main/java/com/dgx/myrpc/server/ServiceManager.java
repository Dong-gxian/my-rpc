package com.dgx.myrpc.server;

import com.dgx.myrpc.Request;
import com.dgx.myrpc.ServiceDescriptor;
import com.dgx.myrpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理中心，提供注册服务、查找服务的功能
 * 服务由提供服务的类和方法组成并定位
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;//服务注册表，服务将注册在这里（没有持久化）
    //用ServiceDescriptor

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();//线程安全的数据结构
    }

    /**
     * 注册服务
     * @param interfaceClass
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, T bean) {//注册函数，把一个接口的的所有公共方法和这个接口的实现类注册成一个服务
        //不同实现类对接口的方法的实现不同，但外界只知道接口，这里的接口应当是用户自定义的服务接口描述了有哪些服务，
        // 需要用反射的方式获取；而bean是一个对象，实现了接口中描述的服务，为啥传进来一个对象而不是实现类？
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            services.put(sdp,sis);
            log.info("register service: {} {}",sdp.getClazz(), sis.getMethod());
        }

    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
