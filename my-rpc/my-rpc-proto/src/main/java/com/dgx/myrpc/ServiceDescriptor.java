package com.dgx.myrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * represent service
 * @author dgx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;//class of service
    private String method;//concrete method of the service class
    private String returnType;
    private String[] parameterTypes;
}
