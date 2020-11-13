package com.dgx.myrpc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * reflection utils class
 * @author dgx
 */
public class ReflectionUtils {
    /**
     *
     * @param clazz class of instance to be created
     * @param <T> type of instance
     * @return instance created
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T newInstance(Class<T> clazz) {
        //The <Te the return value T means that the generics of method can be different from it's class's
        //question:how do we know which constructor to be called?
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * return public methods of a class
     * @param clazz
     * @param <T>
     * @return public methods of a class
     */
    public static <T> Method[] getPublicMethods(Class<T> clazz){
        Method[] declaredMethods = clazz.getDeclaredMethods();
        ArrayList<Method> methods = new ArrayList<Method>();
        for (Method declaredMethod : declaredMethods) {
            if(Modifier.isPublic(declaredMethod.getModifiers())){
                methods.add(declaredMethod);
            }
        }
        return methods.toArray(new Method[0]);
    }

    /**
     * call obj(an instance)'s method
     * @param obj
     * @param method
     * @param args
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object invoke(Object obj, Method method, Object...args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(obj,args);//if this method is static, obj will be null
    }
}
