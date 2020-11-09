package com.dgx.myrpc.server;

import com.dgx.myrpc.Request;
import com.dgx.myrpc.ServiceDescriptor;
import com.dgx.myrpc.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    private ServiceManager sm;
    @Before
    public void init(){
        sm = new ServiceManager();
    }
    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        register();
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        Request request = new Request();
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class,method);
        request.setService(sdp);
        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);
    }
}