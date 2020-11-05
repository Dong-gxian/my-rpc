package com.dgx.myrpc.transport;

/**
 * 1.start, listing
 * 2.receive request
 * 3.close
 * @author dgx
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);
    void start();

    void stop();

}
