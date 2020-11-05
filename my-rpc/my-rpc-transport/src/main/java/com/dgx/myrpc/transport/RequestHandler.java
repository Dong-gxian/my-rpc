package com.dgx.myrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * process requests
 */
public interface RequestHandler { //unimplemented by the framework, must by user?
    void onRequest(InputStream receive, OutputStream toResp);
}
