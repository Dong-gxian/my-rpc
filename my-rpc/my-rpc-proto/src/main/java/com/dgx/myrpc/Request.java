package com.dgx.myrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * represent request of rpc
 * @author dgx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private ServiceDescriptor service;//which service called
    private Object[] parameters;//params given to the service above
}
