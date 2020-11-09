package com.dgx.myrpc;//这些内容是需要在网络上传输的信息，封装成类。

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * peer for internet transport
 * @author dgx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    private String host;
    private int port;
}
