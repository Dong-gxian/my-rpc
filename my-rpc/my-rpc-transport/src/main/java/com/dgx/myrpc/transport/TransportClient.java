package com.dgx.myrpc.transport;

import com.dgx.myrpc.Peer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * 1.create connection
 * 2.send data, wait for response
 * 3.close connection
 * @author dgx
 */
public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data) throws IOException;
    void close();
}
