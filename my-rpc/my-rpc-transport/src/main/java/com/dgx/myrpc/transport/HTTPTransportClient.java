package com.dgx.myrpc.transport;

import com.dgx.myrpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPTransportClient implements TransportClient {
    private String url;

    /**
     * @param peer
     */
    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) throws IOException {
        HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
        //settings
        httpConn.setDoInput(true);
        httpConn.setDoOutput(true);
        httpConn.setUseCaches(false);
        httpConn.setRequestMethod("POST");

        httpConn.connect();//发起连接
        //将data写进httpConn对象的输出流
        IOUtils.copy(data, httpConn.getOutputStream());

        int resultCode = httpConn.getResponseCode();
        if (resultCode == HttpURLConnection.HTTP_OK) {
            return httpConn.getInputStream();
        } else {
            return httpConn.getErrorStream();
        }

    }


    @Override
    public void close() {

    }
}
