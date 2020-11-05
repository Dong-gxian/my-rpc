package com.dgx.myrpc.utils;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() throws InstantiationException, IllegalAccessException {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        System.out.println(t.b());
        assertNotNull(t);
    }

    public void testGetPublicMethods() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,publicMethods.length);
    }

    public void testInvoke() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method m = publicMethods[0];
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        Object i = ReflectionUtils.invoke(t, m);
        assertEquals("b",i);
    }
}