package com.dgx.myrpc.example;

import com.dgx.myrpc.server.RPCServer;

/**
 * @author dgx
 */
public class Server {
    public static void main(String[] args) {
        RPCServer server = new RPCServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
