package com.dgx.myrpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 表示一个具体服务，有目标对象的信息，有目标方法的信息
 * @author DGX
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInstance {
    private Object target;//该服务由哪个实例提供？
    private Method method;//该服务由哪个方法提供？
}
