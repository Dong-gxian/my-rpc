package com.dgx.myrpc.example;

import com.dgx.myrpc.client.RpcClient;

/**
 * @author dgx
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int r1=service.add(1,2);
        int r2 = service.minus(1,5);
        System.out.println(r1);
        System.out.println(r2);
    }
}
